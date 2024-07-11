package lk.ijse.gdse.shehaniRestaurant.bo.custom;

import lk.ijse.gdse.shehaniRestaurant.bo.SuperBO;
import lk.ijse.gdse.shehaniRestaurant.dto.BeerDTO;

import java.sql.SQLException;
import java.util.List;

public interface BeerBO extends SuperBO {
    List<BeerDTO> getAllBeer() throws SQLException, ClassNotFoundException;

    boolean beerItemDelete(String id) throws SQLException, ClassNotFoundException;

    boolean beerItemSave(BeerDTO bear) throws SQLException, ClassNotFoundException;

    BeerDTO searchByBeerId(String id) throws SQLException, ClassNotFoundException;

    boolean beerItemUpdate(BeerDTO bear) throws SQLException, ClassNotFoundException;

    String getNewBeerId() throws SQLException, ClassNotFoundException;
}
