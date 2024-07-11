package lk.ijse.gdse.shehaniRestaurant.bo;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ?new BOFactory() : boFactory;
    }

    public enum BOTypes{
        USER,CUSTOMER,VEHICLE,INGREDIENT,SUPPLIER,FOOD,BEER,PARTTIMPEEMPLOYEE
    }

    public SuperBO GetBO(BOTypes boTypes){
        switch (boTypes){
            case USER :
                return new UserBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case INGREDIENT:
                return new IngredientBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case FOOD:
                return new FoodBOImpl();
            case BEER:
                return new BeerBOImpl();
            case PARTTIMPEEMPLOYEE:
                return new PartTimerEmployeeBOImpl();
            default:
                return null;

        }
    }

}
