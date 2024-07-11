package lk.ijse.gdse.shehaniRestaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PartTimeEmployee {
    private String partTimeEmployeeId;
    private String name;
    private String address;
    private String contact;
    private String workingHour;
    private String perHourSalary;
    private String hireDate;
    private String userId;
    private String active;
}
