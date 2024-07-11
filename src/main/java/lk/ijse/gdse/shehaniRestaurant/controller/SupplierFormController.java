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
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.shehaniRestaurant.bo.BOFactory;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.SupplierBO;
import lk.ijse.gdse.shehaniRestaurant.dto.SupplierDTO;
import lk.ijse.gdse.shehaniRestaurant.view.tdm.SupplierTM;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.List;



public class SupplierFormController {

    @FXML
    public TextField txtSearch;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> coltel;

    @FXML
    private AnchorPane centerNode;

    @FXML
    private TableView<SupplierTM> tbSupplier;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().GetBO(BOFactory.BOTypes.SUPPLIER);

    public void initialize() {
        setCellValueFactory();
        loadAllSupplier();
        loadAllSupplierIds();
        setTableAction();
    }

    private void setTableAction() {
        tbSupplier.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                txtId.setText(newSelection.getId());
                txtName.setText(newSelection.getName());
                txtTel.setText(newSelection.getTel());

            }
        });
    }

    private void loadAllSupplierIds() {
        try {
            List<String> supplierIds =supplierBO.getAllSupplierIds();
            String[] possibleIds = supplierIds.toArray(new String[0]);

            TextFields.bindAutoCompletion(txtSearch,possibleIds);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, " Not Found SupplierIds!").show();
        }
    }

    private void loadAllSupplier() {
        ObservableList<SupplierTM> supObList = FXCollections.observableArrayList();
        try {
            List<SupplierDTO> supplierList = supplierBO.getAllSupplier();
            for (SupplierDTO supplier : supplierList){
                SupplierTM tm = new SupplierTM(
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getTel()
                );
                supObList.add(tm);
            }
            tbSupplier.setItems(supObList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtTel.setText("");
        txtSearch.setText("");
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDeleted = supplierBO.supplierDelete(txtId.getText());
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier Not Deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllSupplier();
            clearFields();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {

        SupplierDTO supplier = new SupplierDTO(
                txtId.getText(),
                txtName.getText(),
                txtTel.getText(),
                "Yes"
        );

        try {
            boolean isUpdated = supplierBO.supplierUpdate(supplier);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllSupplier();
            clearFields();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {

        SupplierDTO supplier = new SupplierDTO(
                txtId.getText(),
                txtName.getText(),
                txtTel.getText(),
                "Yes"
        );

        try {
            boolean isSaved = supplierBO.supplierSave(supplier);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Saved!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier Not Saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllSupplier();
            clearFields();
        }


    }

    public void btnSearchOnActon(ActionEvent actionEvent) {
        String id = txtSearch.getText();

        try {
            SupplierDTO supplier = supplierBO.searchBySupplierId(id);
            if (supplier != null){
                txtId.setText(supplier.getId());
                txtName.setText(supplier.getName());
                txtTel.setText(supplier.getTel());
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        btnSearchOnActon(actionEvent);
    }

    public void searchOnMouseClick(MouseEvent mouseEvent) {
        btnSearchOnActon(new ActionEvent());
    }
}
