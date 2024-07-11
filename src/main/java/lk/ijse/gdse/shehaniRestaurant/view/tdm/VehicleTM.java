package lk.ijse.gdse.shehaniRestaurant.view.tdm;

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

public class VehicleTM {
    private String plateNumber;
    private String type;
    private VehicleColour color;
    private String availability;
    private LocalDate licenseDate;
}
