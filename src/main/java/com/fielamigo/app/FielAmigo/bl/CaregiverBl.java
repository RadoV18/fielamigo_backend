package com.fielamigo.app.FielAmigo.bl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.CaregiverBookingsDao;
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
import com.fielamigo.app.FielAmigo.dto.CaregiverBookedDateDto;
import com.fielamigo.app.FielAmigo.entity.CaregiverBookings;
import com.fielamigo.app.FielAmigo.utils.DateCount;

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
    private CaregiverBookingsDao caregiverBookingsDao;
    
    public CaregiverBl(CaregiverCardDao caregiverCardDao, FaBoardingServiceDao faBoardingServiceDao,
        FaTrainingServiceDao faTrainingServiceDao, FaWalkingServiceDao faWalkingServiceDao,
        FaNursingServiceDao faNursingServiceDao, FaCaregiverDao faCaregiverDao,
        FaCaregiverExperienceDao faCaregiverExperienceDao, FaCaregiverImageDao faCaregiverImageDao,
        CaregiverBookingsDao caregiverBookingsDao
    ) {
        this.caregiverCardDao = caregiverCardDao;
        this.faBoardingServiceDao = faBoardingServiceDao;
        this.faTrainingServiceDao = faTrainingServiceDao;
        this.faWalkingServiceDao = faWalkingServiceDao;
        this.faNursingServiceDao = faNursingServiceDao;
        this.faCaregiverDao = faCaregiverDao;
        this.faCaregiverExperienceDao = faCaregiverExperienceDao;
        this.faCaregiverImageDao = faCaregiverImageDao;
        this.caregiverBookingsDao = caregiverBookingsDao;
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

    /**
     * Method that returns a list of unavailable dates of a caregiver
     * @param caregiverId the id of the caregiver
     * @return a list of unavailable dates
     */
    public CaregiverBookedDateDto getCaregiverUnavailableDates(
            Integer caregiverId, Integer year, Integer month) {
        CaregiverBookedDateDto result = new CaregiverBookedDateDto();
        
        // Date with the first day of the month
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = cal.getTime();

        // Date with the last day of the month
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDayOfMonth = cal.getTime();

        Integer maxDogs = this.faBoardingServiceDao.getMaxDogs(caregiverId);
        result.setMaxDogs(maxDogs);

        List<CaregiverBookings> dates =
            this.caregiverBookingsDao.getCaregiverBookedDates(caregiverId, firstDayOfMonth, lastDayOfMonth);

        // If there are no dates, return an empty list
        if(dates == null) {
            result.setDates(new ArrayList<>());
            return result;
        }

        // Hashmap with the booked dates
        HashMap<Long, Integer> bookedDates = new HashMap<>();
        for (CaregiverBookings booking : dates) {
            Date ending = booking.getEndingDate();
            Integer dogCount = booking.getDogCount();
            for(Date starting = booking.getStartingDate(); starting.before(ending);
                starting = new Date(starting.getTime() + 86400000)
            ) {
                Long time = starting.getTime();
                if(bookedDates.containsKey(time)) {
                    Integer current = bookedDates.get(time);
                    bookedDates.put(time, current + dogCount);
                } else {
                    bookedDates.put(time, dogCount);
                }
            }
        }

        // List of booked dates
        List<DateCount> caregiverBookedDates = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : bookedDates.entrySet()) {
            DateCount dateCount = new DateCount();
            dateCount.setDate(new Date(entry.getKey()));
            dateCount.setCount(entry.getValue());
            caregiverBookedDates.add(dateCount);
        }

        result.setDates(caregiverBookedDates);
        return result;
    }
}
