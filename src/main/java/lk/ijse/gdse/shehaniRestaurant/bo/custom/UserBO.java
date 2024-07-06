package lk.ijse.gdse.shehaniRestaurant.bo.custom;

import lk.ijse.gdse.shehaniRestaurant.bo.SuperBO;
import lk.ijse.gdse.shehaniRestaurant.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean checkUserCredential(String userId, String pw) throws SQLException, IOException, ClassNotFoundException;
    boolean userSave(UserDTO user) throws SQLException, ClassNotFoundException;
    List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;
    Boolean userDelete(String id) throws SQLException, ClassNotFoundException;
    Boolean userUpdate(UserDTO user) throws SQLException, ClassNotFoundException;
    UserDTO SearchByUserId(String id) throws SQLException, ClassNotFoundException;
}
