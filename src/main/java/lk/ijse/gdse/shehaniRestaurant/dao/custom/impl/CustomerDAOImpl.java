package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.CustomerDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> getActiveAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer");
        List<Customer> customers = new ArrayList<>();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String userId = resultSet.getString(5);
            String nic = resultSet.getString(6);
            String active = resultSet.getString(7);

            Customer customer = new Customer(id,name,address,tel,userId,nic,active);
            customers.add(customer);
        }

        return customers;

    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT MAX(CAST(SUBSTRING(CustomerId, 2) AS UNSIGNED)) AS HighestCustomerId FROM Customer");

        if(resultSet.next()) {

            int idNum = resultSet.getInt(1);
            return "C" + ++idNum;
        }
        return "C1";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET Active = 'No' WHERE CustomerId = ?",id);
    }

    @Override
    public Customer searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer WHERE CustomerId = ?",id);
        if (resultSet.next()){
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return null;
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET Name = ?, Address = ?, Contact = ?, UserId = ?, NIC = ?, Active = ? WHERE CustomerId = ?",
                customer.getName(),
                customer.getAddress(),
                customer.getTel(),
                customer.getUsername(),
                customer.getNic(),
                customer.getActive(),
                customer.getId());
    }

    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer VALUES (?, ?, ?, ?, ? , ?, ?)",
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getTel(),
                customer.getUsername(),
                customer.getNic(),
                customer.getActive());
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer WHERE Active = 'Yes'");
        List<Customer> customers = new ArrayList<>();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            String userId = resultSet.getString(5);
            String nic = resultSet.getString(6);
            String active = resultSet.getString(7);

            Customer customer = new Customer(id,name,address,tel,userId,nic,active);
            customers.add(customer);
        }

        return customers;
    }
}
