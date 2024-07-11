package lk.ijse.gdse.shehaniRestaurant.dao.custom;

import lk.ijse.gdse.shehaniRestaurant.dao.CrudDAO;
import lk.ijse.gdse.shehaniRestaurant.dao.SuperDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    boolean checkCredential(String userId, String pw) throws SQLException, ClassNotFoundException;

}
