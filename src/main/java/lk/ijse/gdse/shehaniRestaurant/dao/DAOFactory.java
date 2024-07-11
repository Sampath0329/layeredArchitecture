package lk.ijse.gdse.shehaniRestaurant.dao;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.impl.CustomerBOImpl;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        USER,CUSTOMER,VEHICLE,SUPPLIER,INGREDIENT,FOOD,BEER,INGREDIENTDETAIL,PARTTIMEEMPLOYEE,FULLTIMEREMPLOYEE,ORDER,ORDERDETAIL
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER :
                return new UserDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case INGREDIENT:
                return new IngredientDAOImpl();
            case FOOD:
                return new FoodDAOImpl();
            case BEER:
                return new BeerDAOImpl();
            case INGREDIENTDETAIL:
                return  new IngredientDetailDAOImpl();
            case PARTTIMEEMPLOYEE:
                return new PartTimerEmployeeDAOImpl();
            case FULLTIMEREMPLOYEE:
                return new FullTimeEmployeeDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            default:
                return null;
        }
    }
}
