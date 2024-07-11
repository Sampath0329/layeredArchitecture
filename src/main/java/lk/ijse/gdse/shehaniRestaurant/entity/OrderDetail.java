package lk.ijse.gdse.shehaniRestaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class OrderDetail {
    private String orderId;
    private String foodId;
    private int qty;
    private double unitPrice;
}
