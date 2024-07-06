package lk.ijse.gdse.shehaniRestaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class CustomerDTO {
    private String id;
    private String name;
    private String address;
    private String tel;
    private String username;
    private String nic;
    private String active;
}
