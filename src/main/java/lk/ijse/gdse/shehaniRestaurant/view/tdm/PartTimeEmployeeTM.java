package lk.ijse.gdse.shehaniRestaurant.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PartTimeEmployeeTM {
    private String PartTimeEmployeeId;
    private String Name;
    private String Address;
    private String Contact;
    private String WorkingHour;
    private String PerHourSalary;
    private String HireDate;
    private String UserId;
    private String Active;
}
