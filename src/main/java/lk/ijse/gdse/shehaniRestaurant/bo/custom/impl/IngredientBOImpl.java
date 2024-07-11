package lk.ijse.gdse.shehaniRestaurant.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.gdse.shehaniRestaurant.bo.custom.IngredientBO;
import lk.ijse.gdse.shehaniRestaurant.dao.DAOFactory;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.IngredientDAO;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.IngredientDetailDAO;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.SupplierDAO;
import lk.ijse.gdse.shehaniRestaurant.db.DbConnection;
import lk.ijse.gdse.shehaniRestaurant.dto.IngredientDTO;
import lk.ijse.gdse.shehaniRestaurant.dto.IngredientDetailDTO;
import lk.ijse.gdse.shehaniRestaurant.dto.SupplierDTO;
import lk.ijse.gdse.shehaniRestaurant.entity.Ingredient;
import lk.ijse.gdse.shehaniRestaurant.entity.IngredientDetail;
import lk.ijse.gdse.shehaniRestaurant.entity.Supplier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientBOImpl implements IngredientBO {
    IngredientDetailDAO ingredientDetailDAO = (IngredientDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INGREDIENTDETAIL);
    IngredientDAO ingredientDAO = (IngredientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INGREDIENT);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public List<IngredientDTO> getAllIngredient() throws SQLException, ClassNotFoundException {
        List<Ingredient> ingredients = ingredientDAO.getAll();
        List<IngredientDTO> ingredientDTOList = new ArrayList<>();

        for (Ingredient i : ingredients){
            if (i.getDelete().equals("No")){

                ingredientDTOList.add(new IngredientDTO(

                        i.getIngredientId(),
                        i.getName(),
                        i.getEXP_Date(),
                        i.getQty(),
                        i.getUnit(),
                        i.getPrice(),
                        i.getDelete()
                ));
            }
        }
        return ingredientDTOList;
    }

    @Override
    public List<String> getSupplierIds() throws SQLException, ClassNotFoundException {
        List<Supplier> supplierList = supplierDAO.getAll();
        List<String> supplierIdList = new ArrayList<>();
        for (Supplier s : supplierList){
            if (s.getActive().equals("Yes")){
                supplierIdList.add(s.getId());
            }
        }
        return supplierIdList;
    }


    @Override
    public String generateNextIngredientId() throws SQLException, ClassNotFoundException {
        return ingredientDAO.getNextId();
    }

    @Override
    public IngredientDTO SearchByIngredientId(String id) throws SQLException, ClassNotFoundException {
        Ingredient ingredient = ingredientDAO.searchById(id);
        return new IngredientDTO(
                ingredient.getIngredientId(),
                ingredient.getName(),
                ingredient.getEXP_Date(),
                ingredient.getQty(),
                ingredient.getUnit(),
                ingredient.getPrice(),
                ingredient.getDelete()
                );
    }

    @Override
    public SupplierDTO getSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.searchById(id);
        if (supplier != null){
            return  new SupplierDTO(
                    supplier.getId(),
                    supplier.getName(),
                    supplier.getTel(),
                    supplier.getActive()
            );
        }
        return null;
    }

    @Override
    public IngredientDetailDTO getIngredientDetails(String id) throws SQLException, ClassNotFoundException {
        IngredientDetail ingredientDetailDTO = ingredientDetailDAO.searchById(id);
        if (ingredientDetailDTO != null){
            return  new IngredientDetailDTO(
                    ingredientDetailDTO.getSupplierId(),
                    ingredientDetailDTO.getIngredientId(),
                    ingredientDetailDTO.getDate()
            );
        }
        return null;
    }

    @Override
    public boolean ingredientUpdate(IngredientDTO ingredient, IngredientDetailDTO ingredientDetail) throws SQLException {
        Connection connection = DbConnection.getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isUpdated = ingredientDAO.update(new Ingredient(
                    ingredient.getIngredientId(),
                    ingredient.getName(),
                    ingredient.getEXP_Date(),
                    ingredient.getQty(),
                    ingredient.getUnit(),
                    ingredient.getPrice(),
                    ingredient.getDelete()
                    ));
            if (isUpdated){
                System.out.println(ingredientDetail.getSupplierId());
                boolean isDetailUpdated = ingredientDetailDAO.update(new IngredientDetail(
                        ingredientDetail.getSupplierId(),
                        ingredientDetail.getIngredientId(),
                        ingredientDetail.getDate()
                ));
                if (isDetailUpdated){
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean ingredientDelete(String ingredientId) throws SQLException, ClassNotFoundException {
        return ingredientDAO.delete(ingredientId);
    }

    @Override
    public boolean placeIngredient(IngredientDTO ingredient, IngredientDetailDTO ingredientDetail) throws SQLException {
        Connection connection = DbConnection.getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isSaved = ingredientDAO.save(new Ingredient(
                    ingredient.getIngredientId(),
                    ingredient.getName(),
                    ingredient.getEXP_Date(),
                    ingredient.getQty(),
                    ingredient.getUnit(),
                    ingredient.getPrice(),
                    ingredient.getDelete()
            ));
            if (isSaved){
                boolean isDetailSaved = ingredientDetailDAO.save(new IngredientDetail(
                        ingredientDetail.getSupplierId(),
                        ingredientDetail.getIngredientId(),
                        ingredientDetail.getDate()
                ));
                if (isDetailSaved){
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
