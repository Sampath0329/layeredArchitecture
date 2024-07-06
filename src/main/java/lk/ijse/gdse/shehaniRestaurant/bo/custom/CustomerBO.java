package lk.ijse.gdse.shehaniRestaurant.bo.custom;

import lk.ijse.gdse.shehaniRestaurant.bo.SuperBO;
import lk.ijse.gdse.shehaniRestaurant.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    List<String> getCustomerIds() throws SQLException, ClassNotFoundException;

    List<CustomerDTO> getAllActiveCustomers() throws SQLException, ClassNotFoundException;

    String getNextOrderId() throws SQLException, ClassNotFoundException;

    Boolean customerDelete(String id) throws SQLException, ClassNotFoundException;

    CustomerDTO searchByCustomerId(String id) throws SQLException, ClassNotFoundException;

    Boolean customerUpdate(CustomerDTO customer) throws SQLException, ClassNotFoundException;

    Boolean customerSave(CustomerDTO customer) throws SQLException, ClassNotFoundException;
}
