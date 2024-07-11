package lk.ijse.gdse.shehaniRestaurant.bo.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.SupplierBO;
import lk.ijse.gdse.shehaniRestaurant.dao.DAOFactory;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.SupplierDAO;
import lk.ijse.gdse.shehaniRestaurant.dto.SupplierDTO;
import lk.ijse.gdse.shehaniRestaurant.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public List<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        List<Supplier> suppliers =supplierDAO.getAll();
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        for (Supplier s : suppliers){
            if (s.getActive().equals("Yes")) {
                supplierDTOList.add(new SupplierDTO(
                        s.getId(),
                        s.getName(),
                        s.getTel(),
                        s.getActive()
                ));
            }
        }
        return supplierDTOList;
    }

    @Override
    public List<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        List<Supplier> suppliers =supplierDAO.getAll();
        List<String> supplierIds = new ArrayList<>();
        for (Supplier s : suppliers){
            supplierIds.add(s.getId());
        }
        return supplierIds;
    }

    @Override
    public boolean supplierDelete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean supplierUpdate(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(
                supplier.getId(),
                supplier.getName(),
                supplier.getTel(),
                supplier.getActive()
        ));
    }

    @Override
    public boolean supplierSave(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(
                supplier.getId(),
                supplier.getName(),
                supplier.getTel(),
                supplier.getActive()
        ));
    }

    @Override
    public SupplierDTO searchBySupplierId(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.searchById(id);
        if (supplier != null){
            return new SupplierDTO(
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getTel(),
                    supplier.getActive()
            );
        }
        return null;
    }
}
