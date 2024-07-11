package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.BeerDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.Beer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeerDAOImpl implements BeerDAO {
    @Override
    public List<Beer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM AlcoholFoodItem");
        List<Beer> beers = new ArrayList<>();
        while (resultSet.next()){
            beers.add(new Beer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return beers;
    }

    @Override
    public Beer searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM AlcoholFoodItem WHERE BearId = ?",id);
        return new Beer(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6)
        );
    }

    @Override
    public boolean update(Beer beer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE AlcoholFoodItem SET Name = ?, Price = ?, Availability = ? ,AlcoholContent = ?, AvailableQty = ? WHERE BearId = ?",
                beer.getName(),
                beer.getPrice(),
                beer.getAvailable(),
                beer.getAlcoholContent(),
                beer.getQty(),
                beer.getId()
        );
    }

    @Override
    public boolean save(Beer beer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO AlcoholFoodItem VALUES (?, ?, ?, ?, ? , ?)",
                beer.getId(),
                beer.getName(),
                beer.getPrice(),
                beer.getAvailable(),
                beer.getAlcoholContent(),
                beer.getQty()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE AlcoholFoodItem SET Availability = 'No' WHERE BearId = ?",id);
    }

    @Override
    public String getNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT MAX(CAST(SUBSTRING(BearId, 2) AS UNSIGNED)) AS HighestBeerId FROM AlcoholFoodItem");

        if(resultSet.next()) {

            int idNum = resultSet.getInt(1);
            return "B" + ++idNum;
        }
        return "B1";
    }
}
