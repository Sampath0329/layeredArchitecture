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
import lk.ijse.gdse.shehaniRestaurant.bo.custom.FoodBO;
import lk.ijse.gdse.shehaniRestaurant.dto.FoodItemDTO;
import lk.ijse.gdse.shehaniRestaurant.entity.FoodItem;
import lk.ijse.gdse.shehaniRestaurant.view.tdm.FoodItemTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddFoodItemFormController {

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lblFoodId;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private AnchorPane centerNode;

    @FXML
    private TableColumn<?, ?> colDes;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<FoodItemTM> tblFoodItem;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtname;

    FoodBO foodBO = (FoodBO) BOFactory.getBoFactory().GetBO(BOFactory.BOTypes.FOOD);

    public void initialize(){
        setCellValueFactory();
        loadAllFoodItem();
        generateNextFoodId();
        setTableAction();
    }

    private void setTableAction() {
        tblFoodItem.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            lblFoodId.setText(newSelection.getId());
            txtname.setText(newSelection.getName());
            txtDesc.setText(newSelection.getDesc());
            txtPrice.setText(newSelection.getPrice());
            txtQty.setText(newSelection.getQty());
        });
    }


    private void generateNextFoodId(){
        try {
            lblFoodId.setText(foodBO.generateNextFoodId());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void loadAllFoodItem() {
        ObservableList<FoodItemTM> obList = FXCollections.observableArrayList();
        try {
            List<FoodItemDTO> footItemList = foodBO.getAvailableFoodItem();
            for (FoodItemDTO foodItem : footItemList){
                FoodItemTM tm = new FoodItemTM(
                        foodItem.getId(),
                        foodItem.getName(),
                        foodItem.getDesc(),
                        foodItem.getPrice(),
                        foodItem.getQty(),
                        foodItem.getActive()
                );
                obList.add(tm);
            }
            tblFoodItem.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
//        txtId.setText("");
        generateNextFoodId();
        txtname.setText("");
        txtDesc.setText("");
        txtPrice.setText("");
        txtQty.setText("");
    }

    public void deleteOnAction(ActionEvent actionEvent) {
//        String id = txtId.getText();
        String id = lblFoodId.getText();

        try {
            boolean isDeleted =foodBO.foodItemDelete(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Food Item Delete!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Food Item Not Delete!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllFoodItem();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        FoodItemDTO foodItem = new FoodItemDTO(
//                txtId.getText(),
                lblFoodId.getText(),
                txtname.getText(),
                txtDesc.getText(),
                txtPrice.getText(),
                txtQty.getText(),
                "Active");
        try {
            boolean isUpdated = foodBO.foodItemUpdate(foodItem);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Food Item updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Food Item Not updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllFoodItem();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
//        String id = txtId.getText();
        String id = lblFoodId.getText();
        String name = txtname.getText();
        String desc = txtDesc.getText();
        String price = txtPrice.getText();
        String qty = txtQty.getText();


        FoodItemDTO foodItem = new FoodItemDTO(id,name,desc,price,qty,"Active");
        try {
            boolean isSaved = foodBO.foodItemSave(foodItem);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Food Item saved!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllFoodItem();
        }
    }

    public void txtSearchOnActionId(ActionEvent actionEvent) {
        Search();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        Search();
    }

    private void Search() {
        String id = txtSearch.getText();

        try {
            FoodItemDTO foodItem = foodBO.searchByFoodItemId(id);
            if (foodItem != null){
//                txtId.setText(foodItem.getId());
                lblFoodId.setText(foodItem.getId());
                txtname.setText(foodItem.getName());
                txtDesc.setText(foodItem.getDesc());
                txtPrice.setText(foodItem.getPrice());
                txtQty.setText(foodItem.getQty());

            } else{
                new Alert(Alert.AlertType.ERROR, "Food Item Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void searchOnMouseClick(MouseEvent mouseEvent) {
        Search();
    }

    public void btnBeerAddOnAction(ActionEvent actionEvent) {
        AnchorPane dashBoardCenter = null;
        try {
            dashBoardCenter = FXMLLoader.load(this.getClass().getResource("/lk.ijse.gdse/AddBearItem_Form.fxml"));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

        centerNode.getChildren().clear();
        centerNode.getChildren().add(dashBoardCenter);
    }
}
