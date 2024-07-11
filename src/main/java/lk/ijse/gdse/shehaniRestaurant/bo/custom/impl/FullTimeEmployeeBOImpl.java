package lk.ijse.gdse.shehaniRestaurant.bo.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.FullTimeEmployeeBO;
import lk.ijse.gdse.shehaniRestaurant.dao.DAOFactory;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.FullTimeEmployeeDAO;
import lk.ijse.gdse.shehaniRestaurant.dto.FullTimeEmployeeDTO;
import lk.ijse.gdse.shehaniRestaurant.entity.FullTimeEmployee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FullTimeEmployeeBOImpl implements FullTimeEmployeeBO {
    FullTimeEmployeeDAO fullTimeEmployeeDAO = (FullTimeEmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FULLTIMEREMPLOYEE);
    @Override
    public List<FullTimeEmployeeDTO> getAllFullTimeEmployees() throws SQLException, ClassNotFoundException {
        List<FullTimeEmployeeDTO> fullTimeEmployeeDTOList = new ArrayList<>();
        List<FullTimeEmployee> list = fullTimeEmployeeDAO.getAll();
        for (FullTimeEmployee f : list){
            fullTimeEmployeeDTOList.add(
                    new FullTimeEmployeeDTO(
                            f.getFullTimeEmployeeId(),
                            f.getName(),
                            f.getAddress(),
                            f.getContact(),
                            f.getFixedSalary(),
                            f.getHireDate(),
                            f.getUserId(),
                            f.getActive()
                    ));
        }

        return fullTimeEmployeeDTOList;
    }

    @Override
    public boolean fullTimeEmployeeDelete(String id) throws SQLException, ClassNotFoundException {
        return fullTimeEmployeeDAO.delete(id);
    }

    @Override
    public FullTimeEmployeeDTO searchByFullTimeEmployeeId(String id) throws SQLException, ClassNotFoundException {
        FullTimeEmployee f = fullTimeEmployeeDAO.searchById(id);
        if (f != null){
            return new FullTimeEmployeeDTO(
                    f.getFullTimeEmployeeId(),
                    f.getName(),
                    f.getAddress(),
                    f.getContact(),
                    f.getFixedSalary(),
                    f.getHireDate(),
                    f.getUserId(),
                    f.getActive()
            );
        }
        return null;
    }

    @Override
    public boolean fullTimeEmployeeUpdate(FullTimeEmployeeDTO fullTimeEmployee) throws SQLException, ClassNotFoundException {
        return fullTimeEmployeeDAO.update(new FullTimeEmployee(
                fullTimeEmployee.getFullTimeEmployeeId(),
                fullTimeEmployee.getName(),
                fullTimeEmployee.getAddress(),
                fullTimeEmployee.getContact(),
                fullTimeEmployee.getFixedSalary(),
                fullTimeEmployee.getHireDate(),
                fullTimeEmployee.getUserId(),
                fullTimeEmployee.getActive()
        ));
    }

    @Override
    public boolean fullTimEmployeeSave(FullTimeEmployeeDTO fullTimeEmployee) throws SQLException, ClassNotFoundException {
        return fullTimeEmployeeDAO.save(
                new FullTimeEmployee(
                        fullTimeEmployee.getFullTimeEmployeeId(),
                        fullTimeEmployee.getName(),
                        fullTimeEmployee.getAddress(),
                        fullTimeEmployee.getContact(),
                        fullTimeEmployee.getFixedSalary(),
                        fullTimeEmployee.getHireDate(),
                        fullTimeEmployee.getUserId(),
                        fullTimeEmployee.getActive()
                )
        );
    }
}
