package lk.ijse.gdse.shehaniRestaurant.bo.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.FoodBO;
import lk.ijse.gdse.shehaniRestaurant.dao.DAOFactory;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.FoodDAO;
import lk.ijse.gdse.shehaniRestaurant.dto.FoodItemDTO;
import lk.ijse.gdse.shehaniRestaurant.entity.FoodItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodBOImpl implements FoodBO {
    FoodDAO foodDAO = (FoodDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FOOD);
    @Override
    public List<FoodItemDTO> getAvailableFoodItem() throws SQLException, ClassNotFoundException {
        List<FoodItem> foodItemList = foodDAO.getAll();
        List<FoodItemDTO> foodItemDTOList = new ArrayList<>();
        for (FoodItem f : foodItemList){
            foodItemDTOList.add(new FoodItemDTO(
                    f.getId(),
                    f.getName(),
                    f.getDesc(),
                    f.getPrice(),
                    f.getQty(),
                    f.getActive()
            ));
        }
        return foodItemDTOList;
    }

    @Override
    public boolean foodItemDelete(String id) throws SQLException, ClassNotFoundException {
        return foodDAO.delete(id);
    }

    @Override
    public boolean foodItemUpdate(FoodItemDTO foodItem) throws SQLException, ClassNotFoundException {

        return foodDAO.update(new FoodItem(
                foodItem.getId(),
                foodItem.getName(),
                foodItem.getDesc(),
                foodItem.getPrice(),
                foodItem.getQty(),
                foodItem.getActive()
        ));
    }

    @Override
    public FoodItemDTO searchByFoodItemId(String id) throws SQLException, ClassNotFoundException {
        FoodItem f = foodDAO.searchById(id);
        return new FoodItemDTO(
                f.getId(),
                f.getName(),
                f.getDesc(),
                f.getPrice(),
                f.getQty(),
                f.getActive()
        );
    }

    @Override
    public boolean foodItemSave(FoodItemDTO foodItem) throws SQLException, ClassNotFoundException {
        return foodDAO.save(new FoodItem(
                foodItem.getId(),
                foodItem.getName(),
                foodItem.getDesc(),
                foodItem.getPrice(),
                foodItem.getQty(),
                foodItem.getActive()
        ));
    }

    @Override
    public String generateNextFoodId() throws SQLException, ClassNotFoundException {
        return foodDAO.getNextId();
    }
}
