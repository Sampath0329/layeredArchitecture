package lk.ijse.gdse.shehaniRestaurant.bo.custom;

import lk.ijse.gdse.shehaniRestaurant.bo.SuperBO;
import lk.ijse.gdse.shehaniRestaurant.dto.FoodItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface FoodBO extends SuperBO {
    List<FoodItemDTO> getAvailableFoodItem() throws SQLException, ClassNotFoundException;

    boolean foodItemDelete(String id) throws SQLException, ClassNotFoundException;

    boolean foodItemUpdate(FoodItemDTO foodItem) throws SQLException, ClassNotFoundException;

    FoodItemDTO searchByFoodItemId(String id) throws SQLException, ClassNotFoundException;

    boolean foodItemSave(FoodItemDTO foodItem) throws SQLException, ClassNotFoundException;

    String generateNextFoodId() throws SQLException, ClassNotFoundException;
}
