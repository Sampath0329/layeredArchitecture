package lk.ijse.gdse.shehaniRestaurant.bo.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.VehicleBO;
import lk.ijse.gdse.shehaniRestaurant.dao.DAOFactory;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.VehicleDAO;
import lk.ijse.gdse.shehaniRestaurant.dto.VehicleColour;
import lk.ijse.gdse.shehaniRestaurant.dto.VehicleDTO;
import lk.ijse.gdse.shehaniRestaurant.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    @Override
    public List<VehicleDTO> GetAllVehicles() throws SQLException, ClassNotFoundException {
        List<Vehicle> vehicles =vehicleDAO.getAll();
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for (Vehicle v : vehicles){
            if (v.getDelete().equals("No")) {
                vehicleDTOList.add(
                        new VehicleDTO(
                                v.getPlateNumber(),
                                v.getType(),
                                v.getColour(),
                                v.getAvailability(),
                                v.getLicenseDate(),
                                v.getDelete()
                        )
                );
            }
        }
        return vehicleDTOList;
    }

    @Override
    public List<VehicleColour> getVehicleColour() {
        List<VehicleColour> colourList = new ArrayList<>();

        colourList.add(VehicleColour.RED);
        colourList.add(VehicleColour.BLUE);
        colourList.add(VehicleColour.GREEN);
        colourList.add(VehicleColour.WHITE);

        return colourList;
    }

    @Override
    public VehicleDTO SearchByPlateNumber(String plateNumber) throws SQLException, ClassNotFoundException {
        Vehicle vehicle = vehicleDAO.searchById(plateNumber);
        if (vehicle != null) {
            return new VehicleDTO(
                    vehicle.getPlateNumber(),
                    vehicle.getType(),
                    vehicle.getColour(),
                    vehicle.getAvailability(),
                    vehicle.getLicenseDate(),
                    vehicle.getDelete());
        }
        return null;
    }

    @Override
    public boolean vehicleUpdate(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {

        return vehicleDAO.update(new Vehicle(
                vehicle.getPlateNumber(),
                vehicle.getType(),
                vehicle.getColour(),
                vehicle.getAvailability(),
                vehicle.getLicenseDate(),
                vehicle.getDelete()
        ));
    }

    @Override
    public boolean vehicleSave(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
        return vehicleDAO.save(new Vehicle(
                vehicle.getPlateNumber(),
                vehicle.getType(),
                vehicle.getColour(),
                vehicle.getAvailability(),
                vehicle.getLicenseDate(),
                vehicle.getDelete()
        ));
    }

    @Override
    public boolean vehicleDelete(String plateNumber) throws SQLException, ClassNotFoundException {
        return vehicleDAO.delete(plateNumber);
    }

    @Override
    public List<String> getAllPlateNumbers() throws SQLException, ClassNotFoundException {
        List<Vehicle> vehicles = vehicleDAO.getAll();
        List<String> plateNumbers = new ArrayList<>();
        for (Vehicle v : vehicles){
            plateNumbers.add(v.getPlateNumber());
        }
        return plateNumbers;
    }
}
