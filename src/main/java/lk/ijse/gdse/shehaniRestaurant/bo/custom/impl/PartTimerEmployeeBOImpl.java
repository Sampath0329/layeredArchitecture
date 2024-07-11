package lk.ijse.gdse.shehaniRestaurant.bo.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.PartTimerEmployeeBO;
import lk.ijse.gdse.shehaniRestaurant.dao.DAOFactory;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.PartTimerEmployeeDAO;
import lk.ijse.gdse.shehaniRestaurant.dto.PartTimeEmployeeDTO;
import lk.ijse.gdse.shehaniRestaurant.entity.PartTimeEmployee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartTimerEmployeeBOImpl implements PartTimerEmployeeBO {
    PartTimerEmployeeDAO partTimerEmployeeDAO = (PartTimerEmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PARTTIMEEMPLOYEE);
    @Override
    public List<PartTimeEmployeeDTO> getAllActivePartTimeEmployee() throws SQLException, ClassNotFoundException {
        List<PartTimeEmployee> all = partTimerEmployeeDAO.getAll();
        List<PartTimeEmployeeDTO> partTimeEmployeeDTOList = new ArrayList<>();
        for (PartTimeEmployee p : all){
            if (p.getActive().equals("Active")){
                partTimeEmployeeDTOList.add(
                        new PartTimeEmployeeDTO(
                                p.getPartTimeEmployeeId(),
                                p.getName(),
                                p.getAddress(),
                                p.getContact(),
                                p.getWorkingHour(),
                                p.getPerHourSalary(),
                                p.getHireDate(),
                                p.getUserId(),
                                p.getActive()
                        ));
            }
        }
        return partTimeEmployeeDTOList;
    }

    @Override
    public boolean partTimeEmployeeSave(PartTimeEmployeeDTO partTimeEmployee) throws SQLException, ClassNotFoundException {
        return partTimerEmployeeDAO.save(new PartTimeEmployee(
                partTimeEmployee.getPartTimeEmployeeId(),
                partTimeEmployee.getName(),
                partTimeEmployee.getAddress(),
                partTimeEmployee.getContact(),
                partTimeEmployee.getWorkingHour(),
                partTimeEmployee.getPerHourSalary(),
                partTimeEmployee.getHireDate(),
                partTimeEmployee.getUserId(),
                partTimeEmployee.getActive()
        ));
    }

    @Override
    public PartTimeEmployeeDTO searchByPartTimeEmployeeId(String id) throws SQLException, ClassNotFoundException {
        PartTimeEmployee partTimeEmployee = partTimerEmployeeDAO.searchById(id);
        return new PartTimeEmployeeDTO(
                partTimeEmployee.getPartTimeEmployeeId(),
                partTimeEmployee.getName(),
                partTimeEmployee.getAddress(),
                partTimeEmployee.getContact(),
                partTimeEmployee.getWorkingHour(),
                partTimeEmployee.getPerHourSalary(),
                partTimeEmployee.getHireDate(),
                partTimeEmployee.getUserId(),
                partTimeEmployee.getActive()
        );
    }

    @Override
    public boolean partTimeEmployeeUpdate(PartTimeEmployeeDTO partTimeEmployee) throws SQLException, ClassNotFoundException {

        return partTimerEmployeeDAO.update(
                new PartTimeEmployee(
                        partTimeEmployee.getPartTimeEmployeeId(),
                        partTimeEmployee.getName(),
                        partTimeEmployee.getAddress(),
                        partTimeEmployee.getContact(),
                        partTimeEmployee.getWorkingHour(),
                        partTimeEmployee.getPerHourSalary(),
                        partTimeEmployee.getHireDate(),
                        partTimeEmployee.getUserId(),
                        partTimeEmployee.getActive()
                ));
    }

    @Override
    public boolean partTimeEmployeeDelete(String id) throws SQLException, ClassNotFoundException {
        return partTimerEmployeeDAO.delete(id);
    }
}
