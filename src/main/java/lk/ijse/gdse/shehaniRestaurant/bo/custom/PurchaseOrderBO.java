package lk.ijse.gdse.shehaniRestaurant.bo.custom;

import lk.ijse.gdse.shehaniRestaurant.bo.SuperBO;
import lk.ijse.gdse.shehaniRestaurant.dto.*;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseOrderBO extends SuperBO {
    List<String> getCustomerNIC() throws SQLException, ClassNotFoundException;

    List<String> getCustomerTel() throws SQLException, ClassNotFoundException;

    String getNextOrderId() throws SQLException, ClassNotFoundException;

    List<String> getFoodIds() throws SQLException, ClassNotFoundException;

    List<String> getBeerIds() throws SQLException, ClassNotFoundException;

    FoodItemDTO searchByFoodItemId(String id) throws SQLException, ClassNotFoundException;

    BeerDTO searchByBeerId(String id) throws SQLException, ClassNotFoundException;

    CustomerDTO getCustomer(String tel) throws SQLException, ClassNotFoundException;

    void printBill(String orderID, String o31);

    boolean isAvailableFood(String foodId, int qty) throws SQLException, ClassNotFoundException;

    boolean isAvailableBeer(String foodId, int qty) throws SQLException, ClassNotFoundException;

    Boolean placeOrder(OrderDTO order, List<OrderDetailDTO> orderDetailList);
}
