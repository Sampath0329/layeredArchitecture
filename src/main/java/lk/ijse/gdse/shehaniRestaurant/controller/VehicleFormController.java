package lk.ijse.gdse.shehaniRestaurant.controller;

import com.ctc.wstx.shaded.msv_core.grammar.util.PossibleNamesCollector;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import lk.ijse.gdse.shehaniRestaurant.bo.BOFactory;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.VehicleBO;
import lk.ijse.gdse.shehaniRestaurant.dto.VehicleColour;
import lk.ijse.gdse.shehaniRestaurant.dto.VehicleDTO;
import lk.ijse.gdse.shehaniRestaurant.view.tdm.VehicleTM;
import org.controlsfx.control.textfield.TextFields;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class VehicleFormController {

    public Label lblColour;
    public DatePicker datepicker;
    @FXML
    private TableColumn<?, ?> ColLicenseDate;

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
    private JFXComboBox<VehicleColour> cBoxColour;

    @FXML
    private CheckBox chBoxAvailability;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colColour;

    @FXML
    private TableColumn<?, ?> colLicenseDate;

    @FXML
    private TableColumn<?, ?> colPlateNumber;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<VehicleTM> tblVehicle;

    @FXML
    private TextField txtPlateNumber;

    @FXML
    private TextField txtSearchId;

    @FXML
    private TextField txtType;

    VehicleBO vehicleBO = (VehicleBO) BOFactory.getBoFactory().GetBO(BOFactory.BOTypes.VEHICLE);

    public void initialize(){
        lordVehicleColour();
        setUserNameForLabel();
        lordAllVehicle();
        setTableAction();
        loadAllPlateNumbers();
    }

    private void loadAllPlateNumbers() {
        try {
            List<String> plateNumbers =vehicleBO.getAllPlateNumbers();
            String[] possibleIds = plateNumbers.toArray(new String[0]);

            TextFields.bindAutoCompletion(txtSearchId,possibleIds);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, " Not Found PlateNumbers!").show();
        }
    }


    private void setTableAction() {
        tblVehicle.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            if (newSelection != null) {
                txtPlateNumber.setText(newSelection.getPlateNumber());
                txtType.setText(newSelection.getType());
                datepicker.setValue(newSelection.getLicenseDate());
                cBoxColour.setValue(newSelection.getColor());
                if (newSelection.getAvailability().equals("Yes")){
                    chBoxAvailability.setSelected(true);
                } else {
                    chBoxAvailability.setSelected(false);
                }
            }
        });
    }

    private void setUserNameForLabel() {
        colPlateNumber.setCellValueFactory(new PropertyValueFactory<>("PlateNumber"));
        colType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colColour.setCellValueFactory(new PropertyValueFactory<>("Color"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("Availability"));
        colLicenseDate.setCellValueFactory(new PropertyValueFactory<>("LicenseDate"));
    }

    private void lordAllVehicle() {
        ObservableList<VehicleTM> vehicleTmObservableList = FXCollections.observableArrayList();
        try {
            List<VehicleDTO> vehicleList =vehicleBO.GetAllVehicles();
            for (VehicleDTO vehicle : vehicleList){
                VehicleTM tm = new VehicleTM(
                        vehicle.getPlateNumber(),
                        vehicle.getType(),
                        vehicle.getColour(),
                        vehicle.getAvailability(),
                        vehicle.getLicenseDate()
                );
                vehicleTmObservableList.add(tm);
            }
            tblVehicle.setItems(vehicleTmObservableList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void lordVehicleColour() {
        List<VehicleColour> colourList = vehicleBO.getVehicleColour();
        ObservableList<VehicleColour> vehicleColourObservableList = FXCollections.observableArrayList();

        vehicleColourObservableList.addAll(colourList);


        cBoxColour.setItems(vehicleColourObservableList);


    }

    @FXML
    public void clearOnAction(ActionEvent event) {
        clearFields();

    }

    private void clearFields() {
        txtPlateNumber.setText("");
        txtType.setText("");
        txtSearchId.setText("");
        datepicker.setValue(null);
        cBoxColour.setValue(null);// Clear the combo box selection
        cBoxColour.setFocusColor(null);
        lblColour.setStyle(null);
        chBoxAvailability.setSelected(false);
    }



    @FXML
    public void deleteOnAction(ActionEvent event) {
        String plateNumber = txtPlateNumber.getText();

        try {
            boolean isDeleted = vehicleBO.vehicleDelete(plateNumber);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Vehicle Not Deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            lordAllVehicle();
            clearFields();
        }
    }

    @FXML
    public void saveOnAction(ActionEvent event) {
        String plateNum = txtPlateNumber.getText();
        String type = txtType.getText();
        VehicleColour colour =  cBoxColour.getValue();
        String availability = "NO";
        if (chBoxAvailability.isSelected()){
            availability = "Yes";
        }
        LocalDate licenseDate = datepicker.getValue();

        VehicleDTO vehicle = new VehicleDTO(plateNum,type,colour,availability,licenseDate,"No");

        try {
            boolean isSaved = vehicleBO.vehicleSave(vehicle);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Saved!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Vehicle Not Saved!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            lordAllVehicle();
            clearFields();
        }


    }

    @FXML
    public void txtSearchIdOnAction(ActionEvent event) {
        String plateNumber = txtSearchId.getText();

        try {
            VehicleDTO vehicle = vehicleBO.SearchByPlateNumber(plateNumber);
            if (vehicle != null){
                txtPlateNumber.setText(vehicle.getPlateNumber());
                txtType.setText(vehicle.getType());
                cBoxColour.setValue(vehicle.getColour());
                chBoxAvailability.setSelected(vehicle.getAvailability().equals("Yes"));
                datepicker.setValue(vehicle.getLicenseDate());

            } else {
                new Alert(Alert.AlertType.ERROR, "Vehicle Not Found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    public void updateOnAction(ActionEvent event) {
        String plateNumber = txtPlateNumber.getText();
        String type = txtType.getText();
        VehicleColour colour = cBoxColour.getValue();
        String availability = "No";
        if (chBoxAvailability.isSelected()){
            availability = "Yes";
        }
        LocalDate lDate = datepicker.getValue();

        VehicleDTO vehicle = new VehicleDTO(plateNumber,type,colour,availability,lDate,"No");

        try {
            boolean isUpdated = vehicleBO.vehicleUpdate(vehicle);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Vehicle Not Updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            lordAllVehicle();
            clearFields();
        }
    }

    public void colourSetOnAction(ActionEvent actionEvent) {
        VehicleColour selectedColour = cBoxColour.getValue();
        if (selectedColour != null) {
            switch (selectedColour) {
                case RED:
                    cBoxColour.setFocusColor(Paint.valueOf("RED"));
                    lblColour.setStyle("-fx-background-radius: 100; -fx-background-color: RED;");
                    break;
                case BLUE:
                    cBoxColour.setFocusColor(Paint.valueOf("BLUE"));
                    lblColour.setStyle("-fx-background-radius: 100; -fx-background-color: BLUE;");
                    break;
                case GREEN:
                    cBoxColour.setFocusColor(Paint.valueOf("GREEN"));
                    lblColour.setStyle("-fx-background-radius: 100; -fx-background-color: GREEN;");
                    break;
                case WHITE:
                    cBoxColour.setFocusColor(Paint.valueOf("WHITE"));
                    lblColour.setStyle("-fx-background-radius: 100; -fx-background-color: WHITE;");
                    break;
            }
        }

    }


    public void typeOnAction(ActionEvent actionEvent) {
    }

    public void plateNumberOnAction(ActionEvent actionEvent) {
    }

    public void datepickerOnAction(ActionEvent actionEvent) {
    }

    public void searchOnMouseClick(MouseEvent mouseEvent) {
        txtSearchIdOnAction(new ActionEvent());
    }
}
