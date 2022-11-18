package com.fielamigo.app.FielAmigo.bl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.FaCatalogDao;
import com.fielamigo.app.FielAmigo.dto.CatalogDto;

@Service
public class CatalogBl {

    private FaCatalogDao faCatalogDao;

    public CatalogBl(FaCatalogDao faCatalogDao) {
        this.faCatalogDao = faCatalogDao;
    }
    
    /**
     * Get all the countries
     * @return List of countries
     */
    public List<CatalogDto> getCountries() {
        return faCatalogDao.getData("Pais");
    }

    /**
     * Get all the cities of a country
     * @param countryId the id of the country
     * @return List of cities
     */
    public List<CatalogDto> getCities(int countryId) {
        return faCatalogDao.getCities(countryId);
    }

    /**
     * Get all the states of a country
     * @return List of states
     */
    public List<CatalogDto> getBreeds() {
        return faCatalogDao.getData("Razas de Perros");
    }
}
