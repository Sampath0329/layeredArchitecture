package lk.ijse.gdse.shehaniRestaurant.bo.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.PurchaseOrderBO;
import lk.ijse.gdse.shehaniRestaurant.dao.DAOFactory;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.BeerDAO;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.CustomerDAO;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.FoodDAO;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.OrderDAO;
import lk.ijse.gdse.shehaniRestaurant.dto.*;
import lk.ijse.gdse.shehaniRestaurant.entity.Beer;
import lk.ijse.gdse.shehaniRestaurant.entity.Customer;
import lk.ijse.gdse.shehaniRestaurant.entity.FoodItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    FoodDAO foodDAO = (FoodDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FOOD);
    BeerDAO beerDAO = (BeerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BEER);
    @Override
    public List<String> getCustomerNIC() throws SQLException, ClassNotFoundException {

        List<Customer> customerDTOS = customerDAO.getAll();
        List<String> nicList = new ArrayList<>();
        for (Customer c : customerDTOS){
            nicList.add(c.getNic());
        }
        return nicList;
    }

    @Override
    public List<String> getCustomerTel() throws SQLException, ClassNotFoundException {
        List<Customer> customerDTOS = customerDAO.getAll();
        List<String> telList = new ArrayList<>();
        for (Customer c : customerDTOS){
            telList.add(c.getTel());
        }
        return telList;
    }

    @Override
    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getNextId();
    }

    @Override
    public List<String> getFoodIds() throws SQLException, ClassNotFoundException {
        List<FoodItem> foodItemList=foodDAO.getAll();
        List<String> foodIds = new ArrayList<>();

        for (FoodItem f:foodItemList){
            foodIds.add(f.getId());
        }
        return foodIds;
    }

    @Override
    public List<String> getBeerIds() throws SQLException, ClassNotFoundException {
        List<Beer> foodItemList=beerDAO.getAll();
        List<String> foodIds = new ArrayList<>();

        for (Beer b:foodItemList){
            if (b.getAvailable().equals("Yes")){
                foodIds.add(b.getId());
            }
        }
        return foodIds;
    }

    @Override
    public FoodItemDTO searchByFoodItemId(String id) throws SQLException, ClassNotFoundException {
        FoodItem foodItem = foodDAO.searchById(id);
        return new FoodItemDTO(
                foodItem.getId(),
                foodItem.getName(),
                foodItem.getDesc(),
                foodItem.getPrice(),
                foodItem.getQty(),
                foodItem.getActive()
        );
    }

    @Override
    public BeerDTO searchByBeerId(String id) throws SQLException, ClassNotFoundException {
        Beer beer = beerDAO.searchById(id);
        return new BeerDTO(
                beer.getId(),
                beer.getName(),
                beer.getPrice(),
                beer.getAvailable(),
                beer.getAlcoholContent(),
                beer.getQty()
        );
    }

    @Override
    public CustomerDTO getCustomer(String tel) throws SQLException, ClassNotFoundException {
        Customer c =customerDAO.searchByTel(tel);
        return new CustomerDTO(
                c.getId(),
                c.getName(),
                c.getAddress(),
                c.getTel(),
                c.getUsername(),
                c.getNic(),
                c.getActive()
        );
    }

    @Override
    public void printBill(String orderID, String o31) {

    }

    @Override
    public boolean isAvailableFood(String foodId, int qty) throws SQLException, ClassNotFoundException {
        return foodDAO.isAvailable(foodId,qty);
    }

    @Override
    public boolean isAvailableBeer(String foodId, int qty) throws SQLException, ClassNotFoundException {
        return beerDAO.isAvailable(foodId,qty);
    }

    @Override
    public Boolean placeOrder(OrderDTO order, List<OrderDetailDTO> orderDetailList) {
        return null;
    }

}
