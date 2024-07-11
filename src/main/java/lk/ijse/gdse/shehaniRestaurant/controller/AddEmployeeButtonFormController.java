package lk.ijse.gdse.shehaniRestaurant.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddEmployeeButtonFormController {

    public AnchorPane centerNode;

    public void partTimeEmpOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane vehicleForm = FXMLLoader.load(this.getClass().getResource("/lk.ijse.gdse/PartTimeEmployee_Form.fxml"));

        centerNode.getChildren().clear();
        centerNode.getChildren().add(vehicleForm);
    }

    public void fullTimeEmpOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane vehicleForm = FXMLLoader.load(this.getClass().getResource("/lk.ijse.gdse/FullTimeEmployee_Form.fxml"));

        centerNode.getChildren().clear();
        centerNode.getChildren().add(vehicleForm);
    }
}
