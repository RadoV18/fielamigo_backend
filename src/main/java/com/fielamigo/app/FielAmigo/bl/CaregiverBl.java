package com.fielamigo.app.FielAmigo.bl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.CaregiverCardDao;
import com.fielamigo.app.FielAmigo.dto.CaregiverCardDto;

@Service
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
        int[] ids = new int[caregivers.size()];
        for (int i = 0; i < caregivers.size(); i++) {
            ids[i] = caregivers.get(i);
        }

        return this.caregiverCardDao.getCaregiversInfo(ids);
    }
}
