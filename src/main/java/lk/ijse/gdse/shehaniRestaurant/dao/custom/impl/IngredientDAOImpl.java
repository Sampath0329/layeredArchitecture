package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.IngredientDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAOImpl implements IngredientDAO {
    @Override
    public List<Ingredient> getAll() throws SQLException, ClassNotFoundException {
        List<Ingredient> ingredients = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Ingredient");
        while (resultSet.next()){
            ingredients.add(new Ingredient(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3).toLocalDate(),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7)
            ));
        }
        return ingredients;
    }

    @Override
    public Ingredient searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Ingredient WHERE IngredientId = ?", id);
        if (resultSet.next()){
            return new Ingredient(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3).toLocalDate(),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7)
            );

        }
        return null;
    }

    @Override
    public boolean update(Ingredient ingredient) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE Ingredient SET Name = ?, EXP_Date = ?, Quantity = ?, Unit = ?, UnitPrice = ?, Deleted = 'No' WHERE IngredientId = ?",
                ingredient.getName(),
                ingredient.getEXP_Date(),
                ingredient.getQty(),
                ingredient.getUnit(),
                ingredient.getPrice(),
                ingredient.getIngredientId()
        );
    }

    @Override
    public boolean save(Ingredient ingredient) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Ingredient VALUES (?, ?, ?, ?, ?, ?, ?)",
                ingredient.getIngredientId(),
                ingredient.getName(),
                ingredient.getEXP_Date(),
                ingredient.getQty(),
                ingredient.getUnit(),
                ingredient.getPrice(),
                ingredient.getDelete()
                );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Ingredient SET Deleted = 'Yes' WHERE IngredientId = ?",id);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT MAX(CAST(SUBSTRING(IngredientId, 2) AS UNSIGNED)) AS HighestIngredientId FROM Ingredient");

        if(resultSet.next()) {

            int idNum = resultSet.getInt(1);
            return "I" + ++idNum;
        }
        return "I1";
    }
}
