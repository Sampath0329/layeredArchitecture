package lk.ijse.gdse.shehaniRestaurant.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientTM {
    private String ingredientId;
    private String name;
    private LocalDate EXP_Date;
    private int qty;
    private String unit;
    private double price;
    private String supplierId;
}
