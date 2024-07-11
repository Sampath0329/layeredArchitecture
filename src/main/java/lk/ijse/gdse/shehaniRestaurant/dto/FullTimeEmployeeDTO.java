package lk.ijse.gdse.shehaniRestaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class FullTimeEmployeeDTO {
    private String fullTimeEmployeeId;
    private  String name;
    private  String address;
    private  String contact;
    private  String fixedSalary;
    private  String hireDate;
    private  String userId;
    private  String active;
}
