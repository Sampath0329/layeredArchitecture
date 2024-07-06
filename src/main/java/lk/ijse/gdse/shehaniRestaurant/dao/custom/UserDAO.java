package lk.ijse.gdse.shehaniRestaurant.dao.custom;

import lk.ijse.gdse.shehaniRestaurant.dao.SuperDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO extends SuperDAO {
    boolean checkCredential(String userId, String pw) throws SQLException, ClassNotFoundException;

    Boolean save(User user)throws SQLException, ClassNotFoundException;

    ArrayList<User> getAll()throws SQLException, ClassNotFoundException;

    Boolean delete(String id) throws SQLException, ClassNotFoundException;

    Boolean update(User user) throws SQLException, ClassNotFoundException;

    User SearchById(String id) throws SQLException, ClassNotFoundException;
}
