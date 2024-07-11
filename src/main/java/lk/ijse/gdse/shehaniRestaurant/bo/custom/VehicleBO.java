package lk.ijse.gdse.shehaniRestaurant.bo.custom;

import lk.ijse.gdse.shehaniRestaurant.bo.SuperBO;
import lk.ijse.gdse.shehaniRestaurant.dto.VehicleColour;
import lk.ijse.gdse.shehaniRestaurant.dto.VehicleDTO;

import java.sql.SQLException;
import java.util.List;

public interface VehicleBO extends SuperBO {
    List<VehicleDTO> GetAllVehicles() throws SQLException, ClassNotFoundException;

    List<VehicleColour> getVehicleColour();

    VehicleDTO SearchByPlateNumber(String plateNumber) throws SQLException, ClassNotFoundException;

    boolean vehicleUpdate(VehicleDTO vehicle) throws SQLException, ClassNotFoundException;

    boolean vehicleSave(VehicleDTO vehicle) throws SQLException, ClassNotFoundException;

    boolean vehicleDelete(String plateNumber) throws SQLException, ClassNotFoundException;

    List<String> getAllPlateNumbers() throws SQLException, ClassNotFoundException;
}
