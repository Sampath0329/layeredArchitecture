package lk.ijse.gdse.shehaniRestaurant.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class FullTimeEmployeeTM {
    private String fullTimeEmployeeId;
    private  String name;
    private  String address;
    private  String contact;
    private  String fixedSalary;
    private  String hireDate;
    private  String userId;
    private  String active;
}
