package lk.ijse.gdse.shehaniRestaurant.bo;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.impl.CustomerBOImpl;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ?new BOFactory() : boFactory;
    }

    public enum BOTypes{
        USER,CUSTOMER
    }

    public SuperBO GetBO(BOTypes boTypes){
        switch (boTypes){
            case USER :
                return new UserBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            default:
                return null;

        }
    }

}
