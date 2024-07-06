package lk.ijse.gdse.shehaniRestaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
//import lk.ijse.gdse.shehaniRestaurant.Repository.CustomerRepo;
//import lk.ijse.gdse.shehaniRestaurant.Repository.OrderRepo;

import java.sql.SQLException;
import java.util.Map;

public class DashBoardCenterFormController {

    @FXML
    private AnchorPane centerNode;

    @FXML
    private BarChart<String, Number> chatIncom;

    @FXML
    private BarChart<String, Number> chatOrders;

    @FXML
    private Label lblCustomerCOunt;

    @FXML
    private Label lblIncomeCount;

    @FXML
    private Label lblOrderCount;

    public void initialize(){
        LoadCustomerCount();
//        LoadOrderCount();
//        LoadIncome();
//        addValueToOrderChart();
//        addValueToIncomeChart();
    }


    private void addValueToIncomeChart() {
//        try {
//            Map<String, Double> dailyIncome = OrderRepo.GetDailyIncome();
//            XYChart.Series<String, Number> series = new XYChart.Series<>();
//            for (Map.Entry<String, Double> entry : dailyIncome.entrySet()) {
//                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
//            }
//            chatIncom.getData().add(series);
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "Error loading income chart data: " + e.getMessage()).show();
//        }
    }

    private void addValueToOrderChart() {
//        try {
//            Map<String, Integer> orderCounts = OrderRepo.GetDailyOrderCounts();
//            XYChart.Series<String, Number> series = new XYChart.Series<>();
//            for (Map.Entry<String, Integer> entry : orderCounts.entrySet()) {
//                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
//            }
//            chatOrders.getData().add(series);
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
    }

    private void LoadIncome() {
//        try {
//            double income = OrderRepo.GetTotalIncome();
//            lblIncomeCount.setText(String.valueOf(income));
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
    }

    private void LoadOrderCount() {
//        try {
//            int orderCount = OrderRepo.GetOrderCount();
//            lblOrderCount.setText(String.valueOf(orderCount));
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
    }

    private void LoadCustomerCount() {
//        try {
//            int customerCount = CustomerRepo.GetCustomerCount();
//            lblCustomerCOunt.setText(String.valueOf(customerCount));
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
    }
}
