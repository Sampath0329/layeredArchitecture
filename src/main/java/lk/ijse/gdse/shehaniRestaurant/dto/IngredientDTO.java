package lk.ijse.gdse.shehaniRestaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString

public class IngredientDTO {
    private String ingredientId;
    private String name;
    private LocalDate EXP_Date;
    private int qty;
    private String unit;
    private double price;
    private String delete;

}
