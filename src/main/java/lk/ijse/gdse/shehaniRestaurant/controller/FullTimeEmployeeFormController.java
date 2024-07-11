package lk.ijse.gdse.shehaniRestaurant.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.shehaniRestaurant.bo.BOFactory;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.FullTimeEmployeeBO;
import lk.ijse.gdse.shehaniRestaurant.dto.FullTimeEmployeeDTO;
import lk.ijse.gdse.shehaniRestaurant.view.tdm.FullTimeEmployeeTM;

import java.sql.SQLException;
import java.util.List;

public class FullTimeEmployeeFormController {

    public TextField txtSearchId;
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colHireDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private Label lbUserId;

    @FXML
    private TableView<FullTimeEmployeeTM> tblFullTimeEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtHireDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtTel;

    FullTimeEmployeeBO fullTimeEmployeeBO = (FullTimeEmployeeBO) BOFactory.getBoFactory().GetBO(BOFactory.BOTypes.FULLTIMEREMPLOYEE);

    public void initialize(){
        SetUserIdForLabel();
        setCellValueFactory();
        loadAllFullTimeEmployee();
        setTableAction();
    }
    private void setTableAction() {
        tblFullTimeEmployee.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            txtId.setText(newSelection.getFullTimeEmployeeId());
            txtName.setText(newSelection.getName());
            txtHireDate.setText(newSelection.getHireDate());
            txtSalary.setText(newSelection.getFixedSalary());
            txtTel.setText(newSelection.getContact());
            txtAddress.setText(newSelection.getAddress());
            lbUserId.setText(newSelection.getUserId());
        });
    }
    private void loadAllFullTimeEmployee() {
        ObservableList<FullTimeEmployeeTM> obList = FXCollections.observableArrayList();
        try {
            List<FullTimeEmployeeDTO> fullTimeEmployeeList = fullTimeEmployeeBO.getAllFullTimeEmployees();
            for (FullTimeEmployeeDTO fullTimeEmployee : fullTimeEmployeeList){
                if (fullTimeEmployee.getActive().equals("Active")){
                    FullTimeEmployeeTM tm = new FullTimeEmployeeTM(
                            fullTimeEmployee.getFullTimeEmployeeId(),
                            fullTimeEmployee.getName(),
                            fullTimeEmployee.getAddress(),
                            fullTimeEmployee.getContact(),
                            fullTimeEmployee.getFixedSalary(),
                            fullTimeEmployee.getHireDate(),
                            fullTimeEmployee.getUserId(),
                            fullTimeEmployee.getActive()
                    );
                    obList.add(tm);
                }
            }
            tblFullTimeEmployee.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("FullTimeEmployeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("FixedSalary"));
        colHireDate.setCellValueFactory(new PropertyValueFactory<>("HireDate"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("UserId"));
    }

    private void SetUserIdForLabel() {
        lbUserId.setText("Admin"); // login form eke idn log wenw nm ain krnn
//        lbUserId.setText(LoginFormController.SUserName);
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtHireDate.setText("");
        txtSalary.setText("");
        txtTel.setText("");
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = fullTimeEmployeeBO.fullTimeEmployeeDelete(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Delete!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Employee Not Delete!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllFullTimeEmployee();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        FullTimeEmployeeDTO fullTimeEmployee = new FullTimeEmployeeDTO(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtTel.getText(),
                txtSalary.getText(),
                txtHireDate.getText(),
                lbUserId.getText(),
                "Active"

        );
        try {
            boolean isUpdated = fullTimeEmployeeBO.fullTimeEmployeeUpdate(fullTimeEmployee);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Employee Not updated!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } finally {
            loadAllFullTimeEmployee();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        FullTimeEmployeeDTO fullTimeEmployee = new FullTimeEmployeeDTO(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtTel.getText(),
                txtSalary.getText(),
                txtHireDate.getText(),
                lbUserId.getText(),
                "Active"

        );
        try {
            boolean isSaved = fullTimeEmployeeBO.fullTimEmployeeSave(fullTimeEmployee);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Employee Not saved!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllFullTimeEmployee();
        }
    }

    public void txtSearchOnActionId(ActionEvent actionEvent) {
        searchById();
    }

    private void searchById() {
        String id = txtSearchId.getText();
        try {
            FullTimeEmployeeDTO fullTimeEmployee = fullTimeEmployeeBO.searchByFullTimeEmployeeId(id);
            if (fullTimeEmployee != null){
                txtId.setText(fullTimeEmployee.getFullTimeEmployeeId());
                txtName.setText(fullTimeEmployee.getName());
                txtAddress.setText(fullTimeEmployee.getAddress());
                txtTel.setText(fullTimeEmployee.getContact());
                txtSalary.setText(fullTimeEmployee.getFixedSalary());
                txtHireDate.setText(fullTimeEmployee.getHireDate());
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void txtSearchOnMouseClick(MouseEvent mouseEvent) {
        searchById();
    }
}
