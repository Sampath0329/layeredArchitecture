package lk.ijse.gdse.shehaniRestaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor

public class VehicleDTO {
    private String plateNumber;
    private String type;
    private VehicleColour colour;
    private String availability;
    private LocalDate licenseDate;
    private String delete;

}
