package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.PartTimerEmployeeDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.PartTimeEmployee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartTimerEmployeeDAOImpl implements PartTimerEmployeeDAO {
    @Override
    public List<PartTimeEmployee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM PartTimeEmployee");
        List<PartTimeEmployee> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(
                    new PartTimeEmployee(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9)

                    ));
        }
        return list;
    }

    @Override
    public PartTimeEmployee searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM PartTimeEmployee WHERE PartTimeEmployeeId = ?", id);
        if (resultSet.next()){
            return new PartTimeEmployee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }
        return new PartTimeEmployee(

        );
    }

    @Override
    public boolean update(PartTimeEmployee partTimeEmployee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE PartTimeEmployee SET Name = ?, Address = ?, Contact = ? ,WorkingHour = ?,PerHourSalary = ?, HireDate = ? ,UserId =? , Active = ? WHERE PartTimeEmployeeId = ?",
                partTimeEmployee.getName(),
                partTimeEmployee.getAddress(),
                partTimeEmployee.getContact(),
                partTimeEmployee.getWorkingHour(),
                partTimeEmployee.getPerHourSalary(),
                partTimeEmployee.getHireDate(),
                partTimeEmployee.getUserId(),
                partTimeEmployee.getActive(),
                partTimeEmployee.getPartTimeEmployeeId()
                );
    }

    @Override
    public boolean save(PartTimeEmployee partTimeEmployee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO PartTimeEmployee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                partTimeEmployee.getPartTimeEmployeeId(),
                partTimeEmployee.getName(),
                partTimeEmployee.getAddress(),
                partTimeEmployee.getContact(),
                partTimeEmployee.getWorkingHour(),
                partTimeEmployee.getPerHourSalary(),
                partTimeEmployee.getHireDate(),
                partTimeEmployee.getUserId(),
                partTimeEmployee.getActive());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE PartTimeEmployee SET Active = 'Deactivate' WHERE PartTimeEmployeeId = ?",id);
    }
}
