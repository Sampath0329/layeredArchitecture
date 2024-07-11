package lk.ijse.gdse.shehaniRestaurant.bo.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.CustomerBO;
import lk.ijse.gdse.shehaniRestaurant.dao.DAOFactory;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.CustomerDAO;
import lk.ijse.gdse.shehaniRestaurant.dto.CustomerDTO;
import lk.ijse.gdse.shehaniRestaurant.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        List<Customer> customers = customerDAO.getAll();
        List<String> customerIds = new ArrayList<>();

        for (Customer c : customers){
            customerIds.add(c.getId());
        }

        return customerIds;
    }

    @Override
    public List<CustomerDTO> getAllActiveCustomers() throws SQLException, ClassNotFoundException {

        List<Customer> customers = customerDAO.getActiveAll();
        List<CustomerDTO> cusList = new ArrayList<>();

        for (Customer c : customers){
            if (c.getActive().equals("Yes")){
                CustomerDTO customer = new CustomerDTO(c.getId(),c.getName(),c.getNic(),c.getAddress(),c.getTel(),c.getUsername(),c.getActive());
                cusList.add(customer);
            }
        }

        return cusList;
    }

    @Override
    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        return customerDAO.getNextId();
    }

    @Override
    public Boolean customerDelete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO searchByCustomerId(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchById(id);
        if (customer != null){
            return new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getTel(),
                    customer.getUsername(),
                    customer.getNic(),
                    customer.getActive()
            );
        }

        return null;
    }

    @Override
    public Boolean customerUpdate(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getTel(),
                customer.getUsername(),
                customer.getNic(),
                customer.getActive()));
    }

    @Override
    public Boolean customerSave(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getTel(),
                customer.getUsername(),
                customer.getNic(),
                customer.getActive()
        ));
    }





}
