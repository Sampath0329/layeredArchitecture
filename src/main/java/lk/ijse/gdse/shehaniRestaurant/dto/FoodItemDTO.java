package lk.ijse.gdse.shehaniRestaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FoodItemDTO {
    private String id;
    private String name;
    private String desc;
    private String price;
    private String qty;
    private String active;
}
