package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.FullTimeEmployeeDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.FullTimeEmployee;
import lk.ijse.gdse.shehaniRestaurant.entity.PartTimeEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FullTimeEmployeeDAOImpl implements FullTimeEmployeeDAO {
    @Override
    public List<FullTimeEmployee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM FullTimeEmployee");
        List<FullTimeEmployee> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(
                    new FullTimeEmployee(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8)

                    ));
        }
        return list;    }

    @Override
    public FullTimeEmployee searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM FullTimeEmployee WHERE FullTimeEmployeeId = ?", id);
        if (resultSet.next()){
            return new FullTimeEmployee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return null;
    }

    @Override
    public boolean update(FullTimeEmployee fullTimeEmployee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE FullTimeEmployee SET Name = ?, Address = ?, Contact = ? ,FixedSalary = ?, HireDate = ? ,UserId =? , Active = ? WHERE FullTimeEmployeeId = ?",
                fullTimeEmployee.getName(),
                fullTimeEmployee.getAddress(),
                fullTimeEmployee.getContact(),
                fullTimeEmployee.getFixedSalary(),
                fullTimeEmployee.getHireDate(),
                fullTimeEmployee.getUserId(),
                fullTimeEmployee.getActive(),
                fullTimeEmployee.getFullTimeEmployeeId()
        );
    }

    @Override
    public boolean save(FullTimeEmployee fullTimeEmployee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO FullTimeEmployee VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                fullTimeEmployee.getFullTimeEmployeeId(),
                fullTimeEmployee.getName(),
                fullTimeEmployee.getAddress(),
                fullTimeEmployee.getContact(),
                fullTimeEmployee.getFixedSalary(),
                fullTimeEmployee.getHireDate(),
                fullTimeEmployee.getUserId(),
                fullTimeEmployee.getActive()
                );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE FullTimeEmployee SET Active = 'DeActive' WHERE FullTimeEmployeeId = ?",id);
    }
}
