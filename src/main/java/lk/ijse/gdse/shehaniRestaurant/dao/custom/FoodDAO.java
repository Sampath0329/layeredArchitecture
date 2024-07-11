package lk.ijse.gdse.shehaniRestaurant.dao.custom;

import lk.ijse.gdse.shehaniRestaurant.dao.CrudDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.FoodItem;

import java.sql.SQLException;

public interface FoodDAO extends CrudDAO<FoodItem> {
    String getNextId() throws SQLException, ClassNotFoundException;
}
