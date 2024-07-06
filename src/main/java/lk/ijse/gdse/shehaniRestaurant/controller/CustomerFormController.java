package lk.ijse.gdse.shehaniRestaurant.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse.shehaniRestaurant.bo.BOFactory;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.CustomerBO;
import lk.ijse.gdse.shehaniRestaurant.dto.CustomerDTO;
import lk.ijse.gdse.shehaniRestaurant.view.tdm.CustomerTM;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.List;


public class CustomerFormController {


    @FXML
    private Label lblCustomerId;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private Label lblUserName;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().GetBO(BOFactory.BOTypes.CUSTOMER);



    public void initialize(){
        setCellValueFactory();
        setUserNameForLable();
        loadAllCustomers();
        loadCustomerTel();
        generateNextCustomerId();
        loadCustomerId();
        setTableAction();

    }

    private void setTableAction() {
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                lblCustomerId.setText(newSelection.getId());
                txtName.setText(newSelection.getName());
                txtAddress.setText(newSelection.getAddress());
                txtNIC.setText(newSelection.getNic());
                txtTel.setText(newSelection.getTel());
                lblUserName.setText(newSelection.getUsername());

            }
        });
    }

    private void loadCustomerId() {
        try {
            List<String> customerIds = customerBO.getCustomerIds();

            String[] possibleIds = customerIds.toArray(new String[0]);

            TextFields.bindAutoCompletion(txtSearch,possibleIds);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void generateNextCustomerId() {

        try {
            String nextCustomerId = customerBO.getNextOrderId();

            lblCustomerId.setText(nextCustomerId);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void loadCustomerTel() {

//        if (PlaceorderFormController.customerTel != null){
//            txtTel.setText(PlaceorderFormController.customerTel);
//        }

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDTO> customerList = customerBO.getAllActiveCustomers();
            for (CustomerDTO customer:customerList){
                CustomerTM tm = new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getTel(),
                        customer.getUsername(),
                        customer.getNic()

                );
                obList.add(tm);
            }
            tblCustomer.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setUserNameForLable() {
//        login Form idn enn on nm comment ek ainn krnn
        lblUserName.setText("Admin");
//        lblUserName.setText(LoginFormController.SUserName);

    }


    public void clearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
//        String id = txtId.getText();
        String id = lblCustomerId.getText();

        try {
            Boolean isDeleted = customerBO.customerDelete(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "customer Deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "customer NOt Deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllCustomers();
            setCellValueFactory();
            clearFields();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = lblCustomerId.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        String userName = lblUserName.getText();


        CustomerDTO customer = new CustomerDTO(id,name,address,tel,userName, nic,"Yes");

        try {
            Boolean isUpdated = customerBO.customerUpdate(customer);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            loadAllCustomers();
            clearFields();
        }

    }

    public void saveOnAction(ActionEvent actionEvent) {
//        String id = txtId.getText();
        String id = lblCustomerId.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        String userName = lblUserName.getText();

//        if (isValied()){
//
            CustomerDTO customer = new CustomerDTO(id,name,address,tel,userName,nic,"Yes");

            try {
                Boolean isSaved = customerBO.customerSave(customer);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {

                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } finally {
                loadAllCustomers();
                setCellValueFactory();
                clearFields();
            }
//        } else {
//            new Alert(Alert.AlertType.ERROR, "isValied True Wenne na").show();
//        }
//
//
//
    }


    private void clearFields() {
//        txtId.setText("");
        generateNextCustomerId();
        txtName.setText("");
        txtNIC.setText("");
        txtAddress.setText("");
        txtTel.setText("");
        txtSearch.setText("");

    }

    public void txtSearchOnActionId(ActionEvent actionEvent) {
        String id = txtSearch.getText();

        try {
            CustomerDTO customer = customerBO.searchByCustomerId(id);
            if (customer != null){
//                txtId.setText(customer.getId());

                lblCustomerId.setText(customer.getId());
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtNIC.setText(customer.getNic());
                txtTel.setText(customer.getTel());
                lblUserName.setText(customer.getUsername());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean isValied() {
//        if (!Regex.setTextColor(lk.ijse.ShehaniRestaurant.Util.TextField.Name,txtName)) return false;
//        if (!Regex.setTextColor(lk.ijse.ShehaniRestaurant.Util.TextField.NIC,txtNIC)) return false;
//        if (!Regex.setTextColor(lk.ijse.ShehaniRestaurant.Util.TextField.Address,txtAddress)) return false;
//        if (!Regex.setTextColor(lk.ijse.ShehaniRestaurant.Util.TextField.Contact,txtTel)) return false;
//
        return  true;
    }

    public void txtCustomerNameOnKeyReleased(KeyEvent keyEvent) {
//       Regex.setTextColor(lk.ijse.ShehaniRestaurant.Util.TextField.Name,txtName);
    }

    public void txtCustomerNICOnKeyReleased(KeyEvent keyEvent) {
//        Regex.setTextColor(lk.ijse.ShehaniRestaurant.Util.TextField.NIC,txtNIC);
    }

    public void txtCustomerAddressOnKeyReleased(KeyEvent keyEvent) {
//        Regex.setTextColor(lk.ijse.ShehaniRestaurant.Util.TextField.Address,txtAddress);
    }

    public void txtCustomerTelOnKeyReleased(KeyEvent keyEvent) {
//        Regex.setTextColor(lk.ijse.ShehaniRestaurant.Util.TextField.Contact,txtTel);
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnAllCustomerReportOnAction(ActionEvent actionEvent) {
//        JasperDesign jasperDesign = null;
//        try {
//            jasperDesign = JRXmlLoader.load("src/main/java/lk/ijse/ShehaniRestaurant/Report/CUstomerAll_Report.jrxml");
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//
//            Map<String,Object> data = new HashMap<>();
//            data.put("CustomerId","Yes");
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getConnection());
//            JasperViewer.viewReport(jasperPrint,false);
//        } catch (JRException e) {
//            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
//        }
    }

    public void searchOnMouseClick(MouseEvent mouseEvent) {
        txtSearchOnActionId(new ActionEvent());
    }
}
