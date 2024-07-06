package lk.ijse.gdse.shehaniRestaurant.bo.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.UserBO;
import lk.ijse.gdse.shehaniRestaurant.dao.DAOFactory;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.UserDAO;
import lk.ijse.gdse.shehaniRestaurant.db.DbConnection;
import lk.ijse.gdse.shehaniRestaurant.dto.UserDTO;
import lk.ijse.gdse.shehaniRestaurant.entity.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean checkUserCredential(String userId, String pw) throws SQLException, IOException, ClassNotFoundException {

        return userDAO.checkCredential( userId, pw);
    }
    @Override
    public boolean userSave(UserDTO user) throws SQLException, ClassNotFoundException {

        return userDAO.save(new User(user.getId(),user.getName(),user.getPw(),user.getNic(),"Active"));

    }

    public Boolean userDelete(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }


    public Boolean userUpdate(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(user.getId(),user.getName(), user.getPw(), user.getNic(),user.getActive()));
    }

    @Override
    public UserDTO SearchByUserId(String id) throws SQLException, ClassNotFoundException {
        User user = userDAO.SearchById(id);
        return new UserDTO(user.getId(), user.getName(), user.getPw(), user.getNic(), user.getActive());
    }

    @Override
    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {

        ArrayList<User> all= userDAO.getAll();
        ArrayList<UserDTO> allUsers = new ArrayList<>();
        for (User u : all){
            allUsers.add(
                    new UserDTO(
                            u.getId(),
                            u.getName(),
                            u.getPw(),
                            u.getNic(),
                            u.getActive()
                    )
            );
        }
        return allUsers;

    }
}
