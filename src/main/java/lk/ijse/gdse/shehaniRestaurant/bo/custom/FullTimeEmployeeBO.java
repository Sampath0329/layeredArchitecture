package lk.ijse.gdse.shehaniRestaurant.bo.custom;

import lk.ijse.gdse.shehaniRestaurant.bo.SuperBO;
import lk.ijse.gdse.shehaniRestaurant.dto.FullTimeEmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface FullTimeEmployeeBO extends SuperBO {
    List<FullTimeEmployeeDTO> getAllFullTimeEmployees() throws SQLException, ClassNotFoundException;

    boolean fullTimeEmployeeDelete(String id) throws SQLException, ClassNotFoundException;

    FullTimeEmployeeDTO searchByFullTimeEmployeeId(String id) throws SQLException, ClassNotFoundException;

    boolean fullTimeEmployeeUpdate(FullTimeEmployeeDTO fullTimeEmployee) throws SQLException, ClassNotFoundException;

    boolean fullTimEmployeeSave(FullTimeEmployeeDTO fullTimeEmployee) throws SQLException, ClassNotFoundException;
}
