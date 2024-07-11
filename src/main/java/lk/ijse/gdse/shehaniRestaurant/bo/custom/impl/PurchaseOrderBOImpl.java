package lk.ijse.gdse.shehaniRestaurant.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.PurchaseOrderBO;
import lk.ijse.gdse.shehaniRestaurant.dao.DAOFactory;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.*;
import lk.ijse.gdse.shehaniRestaurant.db.DbConnection;
import lk.ijse.gdse.shehaniRestaurant.dto.*;
import lk.ijse.gdse.shehaniRestaurant.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    FoodDAO foodDAO = (FoodDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FOOD);
    BeerDAO beerDAO = (BeerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BEER);

    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

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
    public Boolean placeOrder(OrderDTO order, List<OrderDetailDTO> orderDetailList) throws SQLException {
        Connection connection = DbConnection.getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isSaved = orderDAO.save(new Order(
                    order.getOrderId(),
                    order.getCustomerId(),
                    order.getOrderDate(),
                    order.getTimePlace(),
                    order.getPaymentAmount()
            ));
            if (isSaved){
                for (OrderDetailDTO od : orderDetailList){
                    boolean isOrderDetailSaved = orderDetailDAO.save(new OrderDetail(
                            od.getOrderId(),
                            od.getFoodId(),
                            od.getQty(),
                            od.getUnitPrice()
                    ));

                    if (isOrderDetailSaved){
                        if (od.getFoodId().charAt(0) == 'F'){
                            boolean isQtyUpdated = foodDAO.foodItemQtyUpdate(od.getFoodId(),od.getQty());
                            if (isQtyUpdated){
                                connection.commit();
                                return true;
                            }
                        }else {
                            boolean isQtyUpdated = beerDAO.beerQtyUpdate(od.getFoodId(),od.getQty());
                            if (isQtyUpdated){
                                connection.commit();
                                return true;
                            }
                        }
                    }
                }
            }
            connection.rollback();
            return false;

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            connection.rollback();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
    }

}
