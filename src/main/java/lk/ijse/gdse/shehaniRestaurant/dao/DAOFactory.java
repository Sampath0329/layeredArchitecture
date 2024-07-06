package lk.ijse.gdse.shehaniRestaurant.dao;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.impl.CustomerBOImpl;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        USER,CUSTOMER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER :
                return new UserDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            default:
                return null;
        }
    }
}
