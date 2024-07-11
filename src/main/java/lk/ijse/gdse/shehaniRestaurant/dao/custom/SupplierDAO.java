package lk.ijse.gdse.shehaniRestaurant.dao.custom;

import lk.ijse.gdse.shehaniRestaurant.dao.CrudDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {
    List<Supplier> getAll() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(Supplier supplier) throws SQLException, ClassNotFoundException;

    boolean save(Supplier supplier) throws SQLException, ClassNotFoundException;

    Supplier searchById(String id) throws SQLException, ClassNotFoundException;
}
