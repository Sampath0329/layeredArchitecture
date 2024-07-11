package lk.ijse.gdse.shehaniRestaurant.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse.shehaniRestaurant.bo.BOFactory;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.PartTimerEmployeeBO;
import lk.ijse.gdse.shehaniRestaurant.dto.PartTimeEmployeeDTO;
import lk.ijse.gdse.shehaniRestaurant.view.tdm.PartTimeEmployeeTM;

import java.sql.SQLException;
import java.util.List;

public class PartTimeEmployeeFormController {

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
    private TableColumn<?, ?> colPerHourSalary;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colWorkingHour;

    @FXML
    private Label lbUserId;

    @FXML
    private TableView<PartTimeEmployeeTM> tblPartTimeEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtHireDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPerHourSalary;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtWorkinHour;

    PartTimerEmployeeBO partTimerEmployeeBO = (PartTimerEmployeeBO) BOFactory.getBoFactory().GetBO(BOFactory.BOTypes.PARTTIMPEEMPLOYEE);

    public void initialize(){
        SetUserIdForLabel();
        setCellValueFactory();
        loadAllPartTimeEmployee();
    }

    private void loadAllPartTimeEmployee() {
        ObservableList<PartTimeEmployeeTM> obList = FXCollections.observableArrayList();
        try {
            List<PartTimeEmployeeDTO> partTimeEmployeeList = partTimerEmployeeBO.getAllActivePartTimeEmployee();
            for (PartTimeEmployeeDTO partTimeEmployee : partTimeEmployeeList){
                PartTimeEmployeeTM tm = new PartTimeEmployeeTM(
                        partTimeEmployee.getPartTimeEmployeeId(),
                        partTimeEmployee.getName(),
                        partTimeEmployee.getAddress(),
                        partTimeEmployee.getContact(),
                        partTimeEmployee.getWorkingHour(),
                        partTimeEmployee.getPerHourSalary(),
                        partTimeEmployee.getHireDate(),
                        partTimeEmployee.getUserId(),
                        partTimeEmployee.getActive()
                );
                obList.add(tm);
            }
            tblPartTimeEmployee.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("PartTimeEmployeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colWorkingHour.setCellValueFactory(new PropertyValueFactory<>("WorkingHour"));
        colPerHourSalary.setCellValueFactory(new PropertyValueFactory<>("PerHourSalary"));
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
        txtPerHourSalary.setText("");
        txtTel.setText("");
        txtWorkinHour.setText("");
    }
    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = partTimerEmployeeBO.partTimeEmployeeDelete(id);
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
            loadAllPartTimeEmployee();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        PartTimeEmployeeDTO partTimeEmployee = new PartTimeEmployeeDTO(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtTel.getText(),
                txtWorkinHour.getText(),
                txtPerHourSalary.getText(),
                txtHireDate.getText(),
                lbUserId.getText(),
                "Active"

        );
        try {
            boolean isUpdated = partTimerEmployeeBO.partTimeEmployeeUpdate(partTimeEmployee);
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
            loadAllPartTimeEmployee();
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        PartTimeEmployeeDTO partTimeEmployee = new PartTimeEmployeeDTO(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtTel.getText(),
                txtWorkinHour.getText(),
                txtPerHourSalary.getText(),
                txtHireDate.getText(),
                lbUserId.getText(),
                "Active"

        );
        try {
            boolean isSaved = partTimerEmployeeBO.partTimeEmployeeSave(partTimeEmployee);
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
            loadAllPartTimeEmployee();
        }
    }

    public void txtSearchOnActionId(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            PartTimeEmployeeDTO partTimeEmployee = partTimerEmployeeBO.searchByPartTimeEmployeeId(id);
            if (partTimeEmployee != null){
                txtId.setText(partTimeEmployee.getPartTimeEmployeeId());
                txtName.setText(partTimeEmployee.getName());
                txtAddress.setText(partTimeEmployee.getAddress());
                txtTel.setText(partTimeEmployee.getContact());
                txtPerHourSalary.setText(partTimeEmployee.getPerHourSalary());
                txtHireDate.setText(partTimeEmployee.getHireDate());
                txtWorkinHour.setText(partTimeEmployee.getWorkingHour());
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
