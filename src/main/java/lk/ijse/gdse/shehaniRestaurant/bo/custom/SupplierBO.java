package lk.ijse.gdse.shehaniRestaurant.bo.custom;

import lk.ijse.gdse.shehaniRestaurant.bo.SuperBO;
import lk.ijse.gdse.shehaniRestaurant.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {
    List<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    List<String> getAllSupplierIds() throws SQLException, ClassNotFoundException;

    boolean supplierDelete(String id) throws SQLException, ClassNotFoundException;

    boolean supplierUpdate(SupplierDTO supplier) throws SQLException, ClassNotFoundException;

    boolean supplierSave(SupplierDTO supplier) throws SQLException, ClassNotFoundException;

    SupplierDTO searchBySupplierId(String id) throws SQLException, ClassNotFoundException;
}
