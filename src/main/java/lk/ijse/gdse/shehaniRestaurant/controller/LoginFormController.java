package lk.ijse.gdse.shehaniRestaurant.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.gdse.shehaniRestaurant.bo.BOFactory;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.UserBO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LoginFormController {

    public static String SUserName;

    public AnchorPane rootNode;

    public TextField txtId;
    public TextField txtPw;
    @FXML
    private Label time;

    @FXML
    private Label date;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().GetBO(BOFactory.BOTypes.USER);

    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateTime();
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
        timeline.play();
        updateDate();
    }

    private void updateDate() {
        LocalDate now = LocalDate.now();
        date.setText(String.valueOf(now));

    }

    private void updateTime() {
        // Get current time and format it
        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        // Update the label text
        time.setText(formattedTime);
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        String UserId = txtId.getText();
        String pw = txtPw.getText();

        try {
            checkCredential(UserId,pw);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void checkCredential(String UserId, String pw) throws SQLException, IOException, ClassNotFoundException {
        boolean b = userBO.checkUserCredential(UserId,pw);
        if (b){
            SUserName= UserId;
            navigateToTheDashboard();
        } else {
            new Alert(Alert.AlertType.ERROR, "sorry! password is incorrect!").show();
        }

    }

    private void navigateToTheDashboard() throws IOException {
//        System.out.println("load");
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/lk.ijse.gdse/Dashboard_Form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        txtPw.requestFocus();
    }

    public void txtPwOnAction(ActionEvent actionEvent) {
        try {
            loginOnAction(actionEvent);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        


    }
}
