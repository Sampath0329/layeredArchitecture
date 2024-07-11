package lk.ijse.gdse.shehaniRestaurant.dao.custom;

import lk.ijse.gdse.shehaniRestaurant.dao.CrudDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    List<Customer> getActiveAll() throws SQLException, ClassNotFoundException;

    String getNextId() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    Customer searchById(String id) throws SQLException, ClassNotFoundException;

    boolean update(Customer customer) throws SQLException, ClassNotFoundException;

    boolean save(Customer customer) throws SQLException, ClassNotFoundException;

    List<Customer> getAll() throws SQLException, ClassNotFoundException;

    Customer searchByTel(String tel) throws SQLException, ClassNotFoundException;
}
