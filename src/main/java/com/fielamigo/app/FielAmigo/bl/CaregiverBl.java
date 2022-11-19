package com.fielamigo.app.FielAmigo.bl;

import java.util.List;

import com.fielamigo.app.FielAmigo.dao.CaregiverCardDao;
import com.fielamigo.app.FielAmigo.dto.CaregiverCardDto;

public class CaregiverBl {

    private CaregiverCardDao caregiverCardDao;
    
    public CaregiverBl(CaregiverCardDao caregiverCardDao) {
        this.caregiverCardDao = caregiverCardDao;
    }

    /**
     * Method that returns a list of CaregiverCardDto objects
     * @param ids list of ids of the caregivers
     */
    public List<CaregiverCardDto> getCaregiversInfo(List<Integer> caregivers) {
        return this.caregiverCardDao.getCaregiversInfo(caregivers);
    }
}
