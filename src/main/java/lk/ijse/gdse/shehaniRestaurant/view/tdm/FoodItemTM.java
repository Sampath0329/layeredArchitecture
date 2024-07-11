package lk.ijse.gdse.shehaniRestaurant.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class FoodItemTM {
    private String id;
    private String name;
    private String desc;
    private String price;
    private String qty;
    private String active;
}
