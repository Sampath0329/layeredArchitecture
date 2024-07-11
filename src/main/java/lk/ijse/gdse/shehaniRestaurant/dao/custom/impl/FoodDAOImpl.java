package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.FoodDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.FoodItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDAOImpl implements FoodDAO {

    @Override
    public List<FoodItem> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM NonAlcoholFoodItem WHERE Active = 'Active'");
        List<FoodItem> foodItemList = new ArrayList<>();
        while (resultSet.next()){
            foodItemList.add(new FoodItem(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return foodItemList;
    }

    @Override
    public FoodItem searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM NonAlcoholFoodItem WHERE FoodId = ?",id);
        resultSet.next();
        return  new FoodItem(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6)
        );

    }

    @Override
    public boolean update(FoodItem foodItem) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE NonAlcoholFoodItem SET Name = ?, Description = ?, Price = ? ,AvailabilityQty = ?, Active = ? WHERE FoodId = ?",
                foodItem.getName(),
                foodItem.getDesc(),
                foodItem.getPrice(),
                foodItem.getQty(),
                foodItem.getActive(),
                foodItem.getId()
        );
    }

    @Override
    public boolean save(FoodItem foodItem) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO NonAlcoholFoodItem VALUES (?, ?, ?, ?, ? , ?)",
                foodItem.getId(),
                foodItem.getName(),
                foodItem.getDesc(),
                foodItem.getPrice(),
                foodItem.getQty(),
                foodItem.getActive()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE NonAlcoholFoodItem SET Active = 'Deactivate' WHERE FoodId = ?");
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT MAX(CAST(SUBSTRING(FoodId, 2) AS UNSIGNED)) AS HighestCustomerId FROM NonAlcoholFoodItem");

        if(resultSet.next()) {

            int idNum = resultSet.getInt(1);
            return "F" + ++idNum;
        }
        return "F1";
    }

    @Override
    public boolean isAvailable(String foodId, int qty) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT AvailabilityQty FROM NonAlcoholFoodItem WHERE FoodId = ?",foodId);
        if (resultSet.next()){
            int dbQty = Integer.parseInt(resultSet.getString(1));
            if (dbQty >= qty){
                return true;
            }
        }
        return false;
    }
}
