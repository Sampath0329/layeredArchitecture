package lk.ijse.gdse.shehaniRestaurant.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class BeerTM {
    private String id ;
    private String name;
    private String price;
    private String available;
    private String alcoholContent;
    private String qty;

}
