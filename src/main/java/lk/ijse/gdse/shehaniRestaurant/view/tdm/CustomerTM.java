package lk.ijse.gdse.shehaniRestaurant.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode

public class CustomerTM {
    private String id;
    private String name;
    private String nic;
    private String address;
    private String tel;
    private String username;



    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", NIC='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


}
