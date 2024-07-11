package lk.ijse.gdse.shehaniRestaurant.dao.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.dao.SQLUtil;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.OrderDetailDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public List<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetail searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        if (orderDetail.getFoodId().charAt(0) == 'F'){
            return SQLUtil.execute("INSERT INTO OrderFoodItemDetail VALUES(?, ?, ?, ?, ?)",
                    orderDetail.getOrderId(),
                    orderDetail.getFoodId(),
                    null,
                    orderDetail.getQty(),
                    orderDetail.getUnitPrice()
            );
        } else {
            return SQLUtil.execute("INSERT INTO OrderFoodItemDetail VALUES(?, ?, ?, ?, ?)",
                    orderDetail.getOrderId(),
                    null,
                    orderDetail.getFoodId(),
                    orderDetail.getQty(),
                    orderDetail.getUnitPrice()
            );
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
