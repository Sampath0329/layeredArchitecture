package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.VehicleDAO;
import lk.ijse.gdse.shehaniRestaurant.dto.VehicleColour;
import lk.ijse.gdse.shehaniRestaurant.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public List getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =  SQLUtil.execute("SELECT * FROM Vehicle ");
        List<Vehicle> vehicles = new ArrayList<>();
        while (resultSet.next()){
            VehicleColour colour = VehicleColour.valueOf(resultSet.getString(3));
            vehicles.add(
                    new Vehicle(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            colour,
                            resultSet.getString(4),
                            resultSet.getDate(5).toLocalDate(),
                            resultSet.getString(6)
                    ));

        }
        return vehicles;
    }

    @Override
    public Vehicle searchById(String plateNumber) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Vehicle WHERE PlateNumber = ?",plateNumber);
        if (resultSet.next()) {
            VehicleColour colour = VehicleColour.valueOf(resultSet.getString(3));
            return new Vehicle(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    colour,
                    resultSet.getString(4),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getString(6)
            );

        }
        return null;
    }

    @Override
    public boolean update(Vehicle v) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE Vehicle SET Type = ?, Color = ?, Availability = ?, LicenseDate = ?, Deleted = ? WHERE PlateNumber = ?",
                v.getType(),
                String.valueOf(v.getColour()),
                v.getAvailability(),
                v.getLicenseDate(),
                v.getDelete(),
                v.getPlateNumber()
        );

    }

    @Override
    public boolean save(Vehicle v) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Vehicle VALUES (?, ?, ?, ?, ?, ?)",
                v.getPlateNumber(),
                v.getType(),
                String.valueOf(v.getColour()),
                v.getAvailability(),
                v.getLicenseDate(),
                v.getDelete()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE Vehicle SET Deleted = 'Yes' WHERE PlateNumber = ?", id
        );
    }


}
