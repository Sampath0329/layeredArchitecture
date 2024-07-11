package lk.ijse.gdse.shehaniRestaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString

public class OrderDTO {
    private String OrderId;
    private String CustomerId;
    private Date OrderDate;
    private Time TimePlace;
    private String PaymentAmount;

}
