package lk.ijse.gdse.shehaniRestaurant.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTM {
    private String id;
    private String name;
    private String pw;
    private String nic;
    private String active;
}
