package lk.ijse.gdse.shehaniRestaurant.bo.custom;

import lk.ijse.gdse.shehaniRestaurant.bo.SuperBO;
import lk.ijse.gdse.shehaniRestaurant.dto.IngredientDTO;
import lk.ijse.gdse.shehaniRestaurant.dto.IngredientDetailDTO;
import lk.ijse.gdse.shehaniRestaurant.dto.SupplierDTO;
import lk.ijse.gdse.shehaniRestaurant.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface IngredientBO extends SuperBO {
    List<IngredientDTO> getAllIngredient() throws SQLException, ClassNotFoundException;

    List<String> getSupplierIds() throws SQLException, ClassNotFoundException;

    String generateNextIngredientId() throws SQLException, ClassNotFoundException;

    IngredientDTO SearchByIngredientId(String id) throws SQLException, ClassNotFoundException;

    SupplierDTO getSupplier(String id) throws SQLException, ClassNotFoundException;

    IngredientDetailDTO getIngredientDetails(String id) throws SQLException, ClassNotFoundException;

    boolean ingredientUpdate(IngredientDTO ingredient, IngredientDetailDTO ingredientDetail) throws SQLException;

    boolean ingredientDelete(String ingredientId) throws SQLException, ClassNotFoundException;

    boolean placeIngredient(IngredientDTO ingredient, IngredientDetailDTO ingredientDetail) throws SQLException;
}
