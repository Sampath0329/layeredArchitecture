package lk.ijse.gdse.shehaniRestaurant.dao.custom;

import lk.ijse.gdse.shehaniRestaurant.dao.CrudDAO;
import lk.ijse.gdse.shehaniRestaurant.entity.Beer;

import java.sql.SQLException;

public interface BeerDAO extends CrudDAO<Beer> {
    String getNewId() throws SQLException, ClassNotFoundException;
}
