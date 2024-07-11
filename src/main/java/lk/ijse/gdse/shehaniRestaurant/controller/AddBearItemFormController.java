package lk.ijse.gdse.shehaniRestaurant.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.shehaniRestaurant.bo.BOFactory;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.BeerBO;
import lk.ijse.gdse.shehaniRestaurant.dto.BeerDTO;
import lk.ijse.gdse.shehaniRestaurant.view.tdm.BeerTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddBearItemFormController {
    @FXML
    public JFXButton btnSearch;
    @FXML
    public Label lblBearId;
    @FXML
    public TextField txtSearch;

    @FXML
    private AnchorPane centerNode;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAlcCon;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<BeerTM> tblBear;

    @FXML
    private TextField txtAlcoholContent;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtname;

    BeerBO beerBO = (BeerBO) BOFactory.getBoFactory().GetBO(BOFactory.BOTypes.BEER);

    public void initialize(){
        setCellValueFactory();
        loadAllBear();
        generateNextFoodId();
        setTableAction();
    }

    private void setTableAction() {
        tblBear.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                lblBearId.setText(newSelection.getId());
                txtname.setText(newSelection.getName());
                txtPrice.setText(newSelection.getPrice());
                txtQty.setText(newSelection.getQty());
                txtAlcoholContent.setText(newSelection.getAlcoholContent());

            }
        });
    }


    private void generateNextFoodId() {
        try {
            lblBearId.setText(beerBO.getNewBeerId());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadAllBear() {
        ObservableList<BeerTM> obList = FXCollections.observableArrayList();
        try {
            List<BeerDTO> bearList = beerBO.getAllBeer();
            for (BeerDTO bear : bearList){
                BeerTM tm = new BeerTM(
                        bear.getId(),
                        bear.getName(),
                        bear.getPrice(),
                        bear.getAvailable(),
                        bear.getAlcoholContent(),
                        bear.getQty()
                );
                obList.add(tm);
            }
            tblBear.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAlcCon.setCellValueFactory(new PropertyValueFactory<>("alcoholContent"));

    }

    @FXML
    void clearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
//        txtId.setText("");
        generateNextFoodId();
        txtname.setText("");
        txtAlcoholContent.setText("");
        txtPrice.setText("");
        txtQty.setText("");
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
//        String id = txtId.getText();
        String id = lblBearId.getText();

        try {
            boolean isDeleted = beerBO.beerItemDelete(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Bear Delete!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Bear Not Delete!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllBear();
        }
    }

    @FXML
    void saveOnAction(ActionEvent event) {
//        String id = txtId.getText();
        String id = lblBearId.getText();
        String name = txtname.getText();
        String price = txtPrice.getText();
        String qty = txtQty.getText();
        String alcoholContent = txtAlcoholContent.getText();

        BeerDTO bear = new BeerDTO(id,name,price,"Yes",alcoholContent,qty);

        try {
            boolean isSaved =beerBO.beerItemSave(bear);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Bear Item saved!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllBear();
        }
    }

    @FXML
    void txtSearchOnActionId(ActionEvent event) {
        String id = txtSearch.getText();

        try {
            BeerDTO bear = beerBO.searchByBeerId(id);
            if (bear != null){
//                txtId.setText(bear.getId());
                lblBearId.setText(bear.getId());
                txtname.setText(bear.getName());
                txtPrice.setText(bear.getPrice());
                txtQty.setText(bear.getQty());
                txtAlcoholContent.setText(bear.getAlcoholContent());

            } else{
                new Alert(Alert.AlertType.ERROR, "Bear Item Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        BeerDTO bear = new BeerDTO(
//                txtId.getText(),
                lblBearId.getText(),
                txtname.getText(),
                txtPrice.getText(),
                "Yes",
                txtAlcoholContent.getText(),
                txtQty.getText());
        try {
            boolean isUpdated = beerBO.beerItemUpdate(bear);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Beat updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Bear Not updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllBear();
        }
    }



    public void searchOnMouseClick(MouseEvent mouseEvent) {
        txtSearchOnActionId(new ActionEvent());
    }

    public void btnFoodButtonOnAction(ActionEvent actionEvent) {
        AnchorPane dashBoardCenter = null;
        try {
            dashBoardCenter = FXMLLoader.load(this.getClass().getResource("/lk.ijse.gdse/AddFoodItem_Form.fxml"));
            centerNode.getChildren().clear();
            centerNode.getChildren().add(dashBoardCenter);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
}
