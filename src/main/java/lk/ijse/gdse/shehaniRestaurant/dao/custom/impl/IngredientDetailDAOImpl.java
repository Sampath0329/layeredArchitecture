package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.IngredientDetailDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.IngredientDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class IngredientDetailDAOImpl implements IngredientDetailDAO {
    @Override
    public List<IngredientDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public IngredientDetail searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM SupplierIngredientDetail WHERE IngredientId = ?", id);
        if (resultSet.next()){
            return new IngredientDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3).toLocalDate()
            );
        }
        return null;
    }

    @Override
    public boolean update(IngredientDetail ingredientDetail) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE SupplierIngredientDetail SET SupplierId = ? WHERE IngredientId = ?",
                ingredientDetail.getSupplierId(),
                ingredientDetail.getIngredientId()
        );
    }

    @Override
    public boolean save(IngredientDetail ingredientDetail) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO SupplierIngredientDetail VALUES (?, ?, ?)",
                ingredientDetail.getSupplierId(),
                ingredientDetail.getIngredientId(),
                ingredientDetail.getDate()
                );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
