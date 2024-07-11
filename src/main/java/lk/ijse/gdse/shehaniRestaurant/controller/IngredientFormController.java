package lk.ijse.gdse.shehaniRestaurant.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.shehaniRestaurant.bo.BOFactory;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.IngredientBO;
import lk.ijse.gdse.shehaniRestaurant.dto.IngredientDTO;
import lk.ijse.gdse.shehaniRestaurant.dto.IngredientDetailDTO;
import lk.ijse.gdse.shehaniRestaurant.dto.SupplierDTO;
import lk.ijse.gdse.shehaniRestaurant.view.tdm.IngredientTM;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class IngredientFormController {

    public Label lblSupplierName;
    public Label lblIngredientId;
    public TextField txtUnitPrice;
    public DatePicker datePickerEXP;
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private AnchorPane centerNode;

    @FXML
    private JFXComboBox<String> cmbSupplierId;

    @FXML
    private TableColumn<?, ?> colEXPDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colUnit;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<IngredientTM> tbIngredient;

    @FXML
    private TextField txtEXP;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearchId;

    @FXML
    private TextField txtUnit;

    IngredientBO ingredientBO = (IngredientBO) BOFactory.getBoFactory().GetBO(BOFactory.BOTypes.INGREDIENT);



    public void initialize(){
        setSupplierIds();
        generateNextIngredientId();
        LoadAllIngredient();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("ingredientId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEXPDate.setCellValueFactory(new PropertyValueFactory<>("EXP_Date"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
    }

    private void LoadAllIngredient() {
        ObservableList<IngredientTM> ingredientObservableList = FXCollections.observableArrayList();
        try {
            List<IngredientDTO> ingredientList = ingredientBO.getAllIngredient();

            for (IngredientDTO ingredient : ingredientList){
                IngredientDetailDTO ingredientDetailDTO = ingredientBO.getIngredientDetails(ingredient.getIngredientId());
                IngredientTM tm = new IngredientTM(
                        ingredient.getIngredientId(),
                        ingredient.getName(),
                        ingredient.getEXP_Date(),
                        ingredient.getQty(),
                        ingredient.getUnit(),
                        ingredient.getPrice(),
                        ingredientDetailDTO.getSupplierId()
                );
                ingredientObservableList.add(tm);
            }
            tbIngredient.setItems(ingredientObservableList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    private void generateNextIngredientId() {
        try {
            lblIngredientId.setText(ingredientBO.generateNextIngredientId());
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void setSupplierIds() {
        ObservableList<String> SupplierObList = FXCollections.observableArrayList();
        try {
            List<String> idList = ingredientBO.getSupplierIds();

            for(String id : idList) {
                SupplierObList.add(id);
            }

            cmbSupplierId.setItems(SupplierObList);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    public void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    public void clearOnAction(ActionEvent event) {
        ClearFields();
        generateNextIngredientId();
    }

    private void ClearFields() {
        lblSupplierName.setText(null);
        cmbSupplierId.setValue("");
        cmbSupplierId.setFocusColor(null);
        txtName.setText(null);
        datePickerEXP.setValue(null);
        txtUnit.setText(null);
        txtUnitPrice.setText(null);
        txtQty.setText(null);
        txtSearchId.setText(null);
    }

    @FXML
    public void cmbSupplierOnAction(ActionEvent event) {
        String id = cmbSupplierId.getValue();
        try {
            SupplierDTO supplier = ingredientBO.getSupplier(id);
            if (supplier != null) {
                lblSupplierName.setText(supplier.getName());
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    public void deleteOnAction(ActionEvent event) {
        String ingredientId = lblIngredientId.getText();

        try {
            boolean isDeleted = ingredientBO.ingredientDelete(ingredientId);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Ingredient Deleted!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Ingredient Not Deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            LoadAllIngredient();
            ClearFields();
            generateNextIngredientId();
        }
    }

    @FXML
    public void saveOnAction(ActionEvent event) {
        String supplierId = cmbSupplierId.getValue();
        String ingredientId = lblIngredientId.getText();
        String name = txtName.getText();
        LocalDate EXP_Date = datePickerEXP.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        String unit = txtUnit.getText();
        double price = Double.parseDouble(txtUnitPrice.getText());
        LocalDate date = LocalDate.now();

        IngredientDTO ingredient = new IngredientDTO(ingredientId,name,EXP_Date,qty,unit,price,"No");
        IngredientDetailDTO ingredientDetail = new IngredientDetailDTO(supplierId,ingredientId,date);


        try {
            boolean isPlaced = ingredientBO.placeIngredient(ingredient,ingredientDetail);
            if (isPlaced){
                new Alert(Alert.AlertType.CONFIRMATION, "Ingredient Saved!").show();
                generateNextIngredientId();
            } else {
                new Alert(Alert.AlertType.WARNING, "Ingredient Saved Unsuccessfully!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            LoadAllIngredient();
            generateNextIngredientId();
            ClearFields();
        }
    }

    @FXML
    public void txtSearchOnAction(ActionEvent event) {
        String id = txtSearchId.getText();

        try {
            IngredientDTO ingredient = ingredientBO.SearchByIngredientId(id);
            if (ingredient != null){
                IngredientDetailDTO ingredientDetailDTO =  ingredientBO.getIngredientDetails(id);

                if (ingredientDetailDTO != null){
                    lblIngredientId.setText(ingredient.getIngredientId());
                    txtName.setText(ingredient.getName());
                    datePickerEXP.setValue(ingredient.getEXP_Date());
                    txtQty.setText(String.valueOf(ingredient.getQty()));
                    txtUnit.setText(ingredient.getUnit());
                    txtUnitPrice.setText(String.valueOf(ingredient.getPrice()));
                    cmbSupplierId.setValue(ingredientDetailDTO.getSupplierId());

                } else {
                    new Alert(Alert.AlertType.ERROR, "Supplier Not Found!").show();
                }

            } else {
                new Alert(Alert.AlertType.ERROR, "Ingredient Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    public void updateOnAction(ActionEvent event) {
        String supplierId = cmbSupplierId.getValue();
        String ingredientId = lblIngredientId.getText();
        String name = txtName.getText();
        LocalDate EXP_Date = datePickerEXP.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        String unit = txtUnit.getText();
        double price = Double.parseDouble(txtUnitPrice.getText());
        LocalDate date = LocalDate.now();

        IngredientDTO ingredient = new IngredientDTO(ingredientId,name,EXP_Date,qty,unit,price,"No");
        IngredientDetailDTO ingredientDetail = new IngredientDetailDTO(supplierId,ingredientId,date);

        try {
            boolean isUpdate = ingredientBO.ingredientUpdate(ingredient,ingredientDetail);
            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION, "Ingredient Update!").show();

            } else {
                new Alert(Alert.AlertType.WARNING, "Ingredient Update Unsuccessfully!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            LoadAllIngredient();
            generateNextIngredientId();
            ClearFields();
        }
    }

    public void searchOnMouseClick(MouseEvent mouseEvent) {
        txtSearchOnAction(new ActionEvent());
    }
}
