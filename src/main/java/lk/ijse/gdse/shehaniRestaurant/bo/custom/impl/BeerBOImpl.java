package lk.ijse.gdse.shehaniRestaurant.bo.custom.impl;

import lk.ijse.gdse.shehaniRestaurant.bo.custom.BeerBO;
import lk.ijse.gdse.shehaniRestaurant.dao.DAOFactory;
import lk.ijse.gdse.shehaniRestaurant.dao.custom.BeerDAO;
import lk.ijse.gdse.shehaniRestaurant.dto.BeerDTO;
import lk.ijse.gdse.shehaniRestaurant.entity.Beer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeerBOImpl implements BeerBO {

    BeerDAO beerDAO = (BeerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BEER);
    @Override
    public List<BeerDTO> getAllBeer() throws SQLException, ClassNotFoundException {
        List<Beer> beers = beerDAO.getAll();
        List<BeerDTO> beerDTOList = new ArrayList<>();
        for (Beer b : beers){
            beerDTOList.add(
                    new BeerDTO(
                            b.getId(),
                            b.getName(),
                            b.getPrice(),
                            b.getAvailable(),
                            b.getAlcoholContent(),
                            b.getQty()
                    )
            );
        }
        return beerDTOList;
    }

    @Override
    public boolean beerItemSave(BeerDTO bear) throws SQLException, ClassNotFoundException {
        return beerDAO.save(new Beer(
                bear.getId(),
                bear.getName(),
                bear.getPrice(),
                bear.getAvailable(),
                bear.getAlcoholContent(),
                bear.getQty()
        ));
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
    public boolean beerItemUpdate(BeerDTO bear) throws SQLException, ClassNotFoundException {
        return beerDAO.update(new Beer(
                bear.getId(),
                bear.getName(),
                bear.getPrice(),
                bear.getAvailable(),
                bear.getAlcoholContent(),
                bear.getQty()
        ));
    }

    @Override
    public String getNewBeerId() throws SQLException, ClassNotFoundException {
        return beerDAO.getNewId();
    }

    @Override
    public boolean beerItemDelete(String id) throws SQLException, ClassNotFoundException {
        return beerDAO.delete(id);
    }
}
