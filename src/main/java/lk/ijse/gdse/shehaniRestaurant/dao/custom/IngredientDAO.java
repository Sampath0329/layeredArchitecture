package lk.ijse.gdse.shehaniRestaurant.dao.custom;

import lk.ijse.gdse.shehaniRestaurant.dao.CrudDAO;
import lk.ijse.gdse.shehaniRestaurant.dao.SuperDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.Ingredient;

import java.sql.SQLException;

public interface IngredientDAO extends CrudDAO<Ingredient> {
    String getNextId() throws SQLException, ClassNotFoundException;
}
