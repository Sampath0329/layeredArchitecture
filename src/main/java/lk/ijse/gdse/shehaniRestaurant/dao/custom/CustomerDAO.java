package lk.ijse.gdse.shehaniRestaurant.dao.custom;

import lk.ijse.gdse.shehaniRestaurant.dao.SuperDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends SuperDAO {
    List<Customer> getAll() throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    Boolean delete(String id) throws SQLException, ClassNotFoundException;

    Customer searchById(String id) throws SQLException, ClassNotFoundException;

    Boolean update(Customer customer) throws SQLException, ClassNotFoundException;

    Boolean save(Customer customer) throws SQLException, ClassNotFoundException;
}
