package lk.ijse.gdse.shehaniRestaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class OrderDetailDTO {
    private String orderId;
    private String foodId;
    private int qty;
    private double unitPrice;
}
