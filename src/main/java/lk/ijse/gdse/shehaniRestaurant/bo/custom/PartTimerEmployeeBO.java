package lk.ijse.gdse.shehaniRestaurant.bo.custom;

import lk.ijse.gdse.shehaniRestaurant.bo.SuperBO;
import lk.ijse.gdse.shehaniRestaurant.dto.PartTimeEmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface PartTimerEmployeeBO extends SuperBO {
    List<PartTimeEmployeeDTO> getAllActivePartTimeEmployee() throws SQLException, ClassNotFoundException;

    boolean partTimeEmployeeSave(PartTimeEmployeeDTO partTimeEmployee) throws SQLException, ClassNotFoundException;

    PartTimeEmployeeDTO searchByPartTimeEmployeeId(String id) throws SQLException, ClassNotFoundException;

    boolean partTimeEmployeeUpdate(PartTimeEmployeeDTO partTimeEmployee) throws SQLException, ClassNotFoundException;

    boolean partTimeEmployeeDelete(String id) throws SQLException, ClassNotFoundException;
}
