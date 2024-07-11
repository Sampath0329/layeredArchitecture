package lk.ijse.gdse.shehaniRestaurant.dao.custom;

import lk.ijse.gdse.shehaniRestaurant.dao.CrudDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    String getNextId() throws SQLException, ClassNotFoundException;
}
