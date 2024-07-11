package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.OrderDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Order searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Orders VALUES(?, ?, ?, ?, ?,?)",
                order.getOrderId(),
                order.getCustomerId(),
                order.getOrderDate(),
                order.getTimePlace(),
                order.getPaymentAmount(),
                null
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT MAX(CAST(SUBSTRING(OrderId, 2) AS UNSIGNED)) AS HighestOrderId FROM Orders");

        if(resultSet.next()) {

            int idNum = resultSet.getInt(1);
            return "O" + ++idNum;
        }
        return "O1";
    }
}
