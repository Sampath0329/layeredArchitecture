package lk.ijse.gdse.shehaniRestaurant.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.shehaniRestaurant.bo.BOFactory;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.UserBO;
import lk.ijse.gdse.shehaniRestaurant.dto.UserDTO;
import lk.ijse.gdse.shehaniRestaurant.view.tdm.UserTM;
//import lk.ijse.ShehaniRestaurant.Model.User;
//import lk.ijse.ShehaniRestaurant.Repository.UserRepo;

import java.sql.SQLException;
import java.util.List;

public class UserFormController {

    @FXML
    private TextField txtSearchId;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPw;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtPw;

    @FXML
    private TextField txtname;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().GetBO(BOFactory.BOTypes.USER);

    public void initialize(){
        setCellValueFactory();
        loadAllUser();
        setTableAction();
    }

    private void setTableAction() {
        tblUser.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                txtId.setText(newSelection.getId());
                txtname.setText(newSelection.getName());
                txtPw.setText(newSelection.getPw());
                txtNIC.setText(newSelection.getNic());

            }
        });
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPw.setCellValueFactory(new PropertyValueFactory<>("pw"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
    }


    private void loadAllUser() {



        ObservableList<UserTM> obList = FXCollections.observableArrayList();
        try {
            List<UserDTO> userList = userBO.getAllUsers();
            for (UserDTO user : userList){
                UserTM tm = new UserTM(
                        user.getId(),
                        user.getName(),
                        user.getPw(),
                        user.getNic(),
                        user.getActive()
                );
                obList.add(tm);

            }
            tblUser.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtname.getText();
        String pw = txtPw.getText();
        String NIC = txtNIC.getText();
        String active = "Active";

        try {
            userBO.userSave(new UserDTO(id,name,pw,NIC,active));
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }



    }


    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    public void popupAlert(Alert.AlertType type, String msg){
        new Alert(type,msg).show();
    }
    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            Boolean isDeleted = userBO.userDelete(id);
            if (isDeleted){
                popupAlert(Alert.AlertType.CONFIRMATION,"User Deleted!");
            } else {
                popupAlert(Alert.AlertType.ERROR,"UUser Not Deleted!");
            }
        } catch (SQLException  | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllUser();
            clearFields();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        UserDTO userDTO = new UserDTO(
                txtId.getText(),
                txtname.getText(),
                txtPw.getText(),
                txtNIC.getText(),
                "Active");
        try {
            Boolean isUpdated = userBO.userUpdate(userDTO);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "User updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "User Not updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllUser();
        }

    }
    private void clearFields() {
        txtId.setText("");
        txtPw.setText("");
        txtNIC.setText("");
        txtname.setText("");
    }

    public void txtSearchOnActionId(ActionEvent actionEvent) {
        String id = txtSearchId.getText();

        try {
            UserDTO user = userBO.SearchByUserId(id);
            if (user != null){
                txtId.setText(user.getId());
                txtname.setText(user.getName());
                txtPw.setText(user.getPw());
                txtNIC.setText(user.getNic());

            } else{
                new Alert(Alert.AlertType.ERROR, "User Nno Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void searchOnMouseClick(MouseEvent mouseEvent) {
        txtSearchOnActionId(new ActionEvent());
    }
}
