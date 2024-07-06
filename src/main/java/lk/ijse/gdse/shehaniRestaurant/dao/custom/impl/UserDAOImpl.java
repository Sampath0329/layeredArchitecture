package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;


import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.UserDAO;
import lk.ijse.gdse.shehaniRestaurant.db.DbConnection;
import lk.ijse.gdse.shehaniRestaurant.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean checkCredential(String userId, String pw) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT UserId, Password FROM User WHERE UserId = ?", userId);

        if (resultSet.next()){
            if (userId.equals(resultSet.getString(1))){
                if (pw.equals(resultSet.getString(2))){
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public Boolean save(User user) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO User VALUE (?, ?, ?, ?, ?)",user.getId(),user.getName(),user.getPw(),user.getNic(),user.getActive());

    }
    @Override
    public ArrayList<User> getAll()throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM User WHERE Active = 'Active'");

        ArrayList<User> userList = new ArrayList<>();

        while (resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String pw = resultSet.getString(3);
            String NIC = resultSet.getString(4);
            String active = resultSet.getString(5);

            userList.add(new User(id,name,pw,NIC,active));


        }
        return userList;
    }
    @Override
    public Boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE User SET Active = ? WHERE UserId = ?","Deactivate",id);
    }

    public Boolean update(User user) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE User SET Name = ?, Password = ?, NIC = ? , Active = ? WHERE UserId = ?",
                user.getName(),
                user.getPw(),
                user.getNic(),
                user.getActive(),
                user.getId());
    }

    @Override
    public User SearchById(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM User WHERE UserId = ?",id);

        resultSet.next();
        return new User(resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5));

    }

}
