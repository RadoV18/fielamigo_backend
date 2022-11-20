package com.fielamigo.app.FielAmigo.bl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.CaregiverCardDao;
import com.fielamigo.app.FielAmigo.dao.FaBoardingServiceDao;
import com.fielamigo.app.FielAmigo.dao.FaCaregiverDao;
import com.fielamigo.app.FielAmigo.dao.FaCaregiverExperienceDao;
import com.fielamigo.app.FielAmigo.dao.FaCaregiverImageDao;
import com.fielamigo.app.FielAmigo.dao.FaNursingServiceDao;
import com.fielamigo.app.FielAmigo.dao.FaTrainingServiceDao;
import com.fielamigo.app.FielAmigo.dao.FaWalkingServiceDao;
import com.fielamigo.app.FielAmigo.dto.CaregiverCardDto;
import com.fielamigo.app.FielAmigo.dto.CaregiverServiceDto;

@Service
public class CaregiverBl {

    private CaregiverCardDao caregiverCardDao;
    private FaBoardingServiceDao faBoardingServiceDao;
    private FaTrainingServiceDao faTrainingServiceDao;
    private FaWalkingServiceDao faWalkingServiceDao;
    private FaNursingServiceDao faNursingServiceDao;
    private FaCaregiverDao faCaregiverDao;
    private FaCaregiverExperienceDao faCaregiverExperienceDao;
    private FaCaregiverImageDao faCaregiverImageDao;
    
    public CaregiverBl(CaregiverCardDao caregiverCardDao, FaBoardingServiceDao faBoardingServiceDao,
        FaTrainingServiceDao faTrainingServiceDao, FaWalkingServiceDao faWalkingServiceDao,
        FaNursingServiceDao faNursingServiceDao, FaCaregiverDao faCaregiverDao,
        FaCaregiverExperienceDao faCaregiverExperienceDao, FaCaregiverImageDao faCaregiverImageDao
    ) {
        this.caregiverCardDao = caregiverCardDao;
        this.faBoardingServiceDao = faBoardingServiceDao;
        this.faTrainingServiceDao = faTrainingServiceDao;
        this.faWalkingServiceDao = faWalkingServiceDao;
        this.faNursingServiceDao = faNursingServiceDao;
        this.faCaregiverDao = faCaregiverDao;
        this.faCaregiverExperienceDao = faCaregiverExperienceDao;
        this.faCaregiverImageDao = faCaregiverImageDao;
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

    /**
     * Method that returns the list of services offered by a caregiver
     * @param caregiverId the id of the caregiver
     */
    public List<CaregiverServiceDto> getCaregiverServices(int caregiverId) {
        List<CaregiverServiceDto> services = new ArrayList<>();
        CaregiverServiceDto boarding = this.faBoardingServiceDao.getService(caregiverId);
        if(boarding != null) {
            boarding.setName("Alojamiento");
            services.add(boarding);
        }

        CaregiverServiceDto training = this.faTrainingServiceDao.getService(caregiverId);
        if(training != null) {
            training.setName("Entrenamiento");
            services.add(training);
        }

        CaregiverServiceDto walking = this.faWalkingServiceDao.getService(caregiverId);
        if(walking != null) {
            walking.setName("Paseo");
            services.add(walking);
        }

        CaregiverServiceDto nursing = this.faNursingServiceDao.getService(caregiverId);
        if(nursing != null) {
            nursing.setName("Cuidado");
            services.add(nursing);
        }

        return services;
    }

    /**
     * Method that returns the bio of a caregiver
     * @param caregiverId the id of the caregiver
     */
    public String getCaregiverBioById(int caregiverId) {
        return this.faCaregiverDao.getCaregiverBio(caregiverId);
    }

    /**
     * Method that returns a list of experience details of a caregiver
     * @param caregiverId the id of the caregiver
     * @return a list of Strings
     */
    public List<String> getCaregiverExperienceById(int caregiverId) {
        return this.faCaregiverExperienceDao.getCaregiverExperience(caregiverId);
    }

    /**
     * Method that returns the URLs of a caregiver's pictures
     * @param caregiverId the id of the caregiver
     * @return a list of Strings
     */
    public List<String> getCaregiverPicturesById(int caregiverId) {
        return this.faCaregiverImageDao.getCaregiverPictures(caregiverId);
    }
}
