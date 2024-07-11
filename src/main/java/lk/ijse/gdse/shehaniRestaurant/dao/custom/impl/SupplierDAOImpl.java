package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.SupplierDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Supplier");
        List<Supplier> supplierList = new ArrayList<>();
        while (resultSet.next()){
            supplierList.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return supplierList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Supplier SET Active = 'No' WHERE SupplierId = ?",id);
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Supplier SET Name = ? , Contact = ?, Active = ? WHERE SupplierId = ?",
                supplier.getName(),
                supplier.getTel(),
                supplier.getActive(),
                supplier.getId()
        );
    }

    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Supplier VALUES (?, ?, ?, ?)",
                supplier.getId(),
                supplier.getName(),
                supplier.getTel(),
                supplier.getActive()
        );
    }

    @Override
    public Supplier searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * from Supplier where SupplierId = ?",id);
        if (resultSet.next()){
            return new Supplier(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }
}
