package lk.ijse.gdse.shehaniRestaurant.entity;

import lk.ijse.gdse.shehaniRestaurant.dto.VehicleColour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Vehicle {
    private String plateNumber;
    private String type;
    private VehicleColour colour;
    private String availability;
    private LocalDate licenseDate;
    private String delete;

}
