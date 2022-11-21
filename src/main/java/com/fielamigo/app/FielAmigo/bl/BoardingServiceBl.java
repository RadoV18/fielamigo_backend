package com.fielamigo.app.FielAmigo.bl;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.FaBoardingServiceDao;
import com.fielamigo.app.FielAmigo.dto.BoardingServiceDto;
import com.fielamigo.app.FielAmigo.entity.FaBoardingService;

@Service
public class BoardingServiceBl {
    private FaBoardingServiceDao faBoardingServiceDao;

    public BoardingServiceBl(FaBoardingServiceDao faBoardingServiceDao) {
        this.faBoardingServiceDao = faBoardingServiceDao;
    }

    /**
     * creates boarding service on database
    */
    public void  createBoardingService(int caregiverId, BoardingServiceDto boardingServiceDto) {
        FaBoardingService faBoardingService = new FaBoardingService();

        // sets data from body to entity
        faBoardingService.setCaregiverId(caregiverId);
        faBoardingService.setNightlyRate(boardingServiceDto.getNightlyRate());
        faBoardingService.setMaxNumberOfNights(boardingServiceDto.getMaxNumberOfNights());
        faBoardingService.setMaxNumberOfDogs(boardingServiceDto.getMaxNumberOfDogs());
        faBoardingService.setCanPickup(boardingServiceDto.isCanPickup());
        faBoardingService.setPickupRate(boardingServiceDto.getPickupRate());


        // saves entity to database
        faBoardingServiceDao.createBoardingService(faBoardingService);
    }
}
