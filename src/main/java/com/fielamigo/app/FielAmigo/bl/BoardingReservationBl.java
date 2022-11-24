package com.fielamigo.app.FielAmigo.bl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.fielamigo.app.FielAmigo.dao.FaBoardingReservationDao;
import com.fielamigo.app.FielAmigo.dto.CaregiverBookingsDto;
import com.fielamigo.app.FielAmigo.dto.DogDto;
import com.fielamigo.app.FielAmigo.dto.DogSimpleDto;
import com.fielamigo.app.FielAmigo.dto.OwnerBookingsDto;
import com.fielamigo.app.FielAmigo.dto.ReservationInfoDto;
import com.fielamigo.app.FielAmigo.entity.OwnerReservations;
import com.fielamigo.app.FielAmigo.entity.ReservationInfoEntity;


@Service
public class BoardingReservationBl {
    FaBoardingReservationDao faBoardingReservationDao;

    public BoardingReservationBl(FaBoardingReservationDao faBoardingReservationDao) {
        this.faBoardingReservationDao = faBoardingReservationDao;
    }

    public List<OwnerBookingsDto> getOwnerBookings(int userId) {
        List<OwnerBookingsDto> result = new ArrayList<OwnerBookingsDto>();
    
        List<OwnerReservations> reservations = faBoardingReservationDao.showOwnerBookings(userId);

        Map<Integer, OwnerBookingsDto> reservationMap = new HashMap<>();
        for (OwnerReservations reservation : reservations) {
            if(reservationMap.containsKey(reservation.getBoardingReservationId())) {
                // agregar el perro a lo que ya tienes
                OwnerBookingsDto reservationDto = reservationMap.get(reservation.getBoardingReservationId());
                reservationDto.getDogs().add(new DogSimpleDto(reservation.getDogId(), reservation.getDogName()));
            } else {
                // crear el dto y agregarlo al map
                OwnerBookingsDto res = new OwnerBookingsDto();
                res.setBoardingReservationId(reservation.getBoardingReservationId());
                res.setCaregiverName(reservation.getCaregiverName());
                res.setDate(reservation.getDate());
                res.setReservationStatus(reservation.getReservationStatus());
                res.setDogs(new ArrayList<>());
    
                // crear el perro
                DogSimpleDto dog = new DogSimpleDto();
                dog.setDogId(reservation.getDogId());
                dog.setName(reservation.getDogName());
    
                // agregar el perro al dto
                res.getDogs().add(dog);
    
                reservationMap.put(reservation.getBoardingReservationId(), res);
            }   
        }
    
        result.addAll(reservationMap.values());

        result.forEach(resulta -> {
            resulta.setServiceType("Alojamiento");
        });
        return result;
    }

    /**
     * Cancel a reservation
    */
    public void cancelReservation(int reservationId) {
        faBoardingReservationDao.CancelReservation(reservationId);
    }
    
    /**
     * Mark as a completed reservation
    */
    public void completingReservation(int reservationId) {
        faBoardingReservationDao.CompleteReservation(reservationId);
    }

    /**
     * Caregiver accepting reservation
    */
    public void acceptingReservation(int reservationId) {
        faBoardingReservationDao.AcceptReservation(reservationId);
    }

    /**
     * Accepted reservations
     *   
    */
    public List<CaregiverBookingsDto> getAcceptedBookings(int userId) {
        List<CaregiverBookingsDto> result = new ArrayList<CaregiverBookingsDto>();
    
        List<OwnerReservations> reservations = faBoardingReservationDao.showAcceptedBookings(userId);

        Map<Integer, CaregiverBookingsDto> reservationMap = new HashMap<>();
        for (OwnerReservations reservation : reservations) {
            if(reservationMap.containsKey(reservation.getBoardingReservationId())) {
                // agregar el perro a lo que ya tienes
                CaregiverBookingsDto reservationDto = reservationMap.get(reservation.getBoardingReservationId());
                reservationDto.getDogs().add(new DogSimpleDto(reservation.getDogId(), reservation.getDogName()));
            } else {
                // crear el dto y agregarlo al map
                CaregiverBookingsDto res = new CaregiverBookingsDto();
                res.setBoardingReservationId(reservation.getBoardingReservationId());
                res.setCaregiverName(reservation.getCaregiverName());
                res.setDate(reservation.getDate());
                res.setReservationStatus(reservation.getReservationStatus());
                res.setDogs(new ArrayList<>());
    
                // crear el perro
                DogSimpleDto dog = new DogSimpleDto();
                dog.setDogId(reservation.getDogId());
                dog.setName(reservation.getDogName());
    
                // agregar el perro al dto
                res.getDogs().add(dog);
    
                reservationMap.put(reservation.getBoardingReservationId(), res);
            }   
        }
    
        result.addAll(reservationMap.values());

        result.forEach(resulta -> {
            resulta.setServiceType("Alojamiento");
        });
        return result;
    }

    /**
     * New reservations
     *   
    */
    public List<CaregiverBookingsDto> getNewBookings(int userId) {
        List<CaregiverBookingsDto> result = new ArrayList<CaregiverBookingsDto>();
    
        List<OwnerReservations> reservations = faBoardingReservationDao.showNewBookings(userId);

        Map<Integer, CaregiverBookingsDto> reservationMap = new HashMap<>();
        for (OwnerReservations reservation : reservations) {
            if(reservationMap.containsKey(reservation.getBoardingReservationId())) {
                // agregar el perro a lo que ya tienes
                CaregiverBookingsDto reservationDto = reservationMap.get(reservation.getBoardingReservationId());
                reservationDto.getDogs().add(new DogSimpleDto(reservation.getDogId(), reservation.getDogName()));
            } else {
                // crear el dto y agregarlo al map
                CaregiverBookingsDto res = new CaregiverBookingsDto();
                res.setBoardingReservationId(reservation.getBoardingReservationId());
                res.setCaregiverName(reservation.getCaregiverName());
                res.setDate(reservation.getDate());
                res.setReservationStatus(reservation.getReservationStatus());
                res.setDogs(new ArrayList<>());
    
                // crear el perro
                DogSimpleDto dog = new DogSimpleDto();
                dog.setDogId(reservation.getDogId());
                dog.setName(reservation.getDogName());
    
                // agregar el perro al dto
                res.getDogs().add(dog);
    
                reservationMap.put(reservation.getBoardingReservationId(), res);
            }   
        }
    
        result.addAll(reservationMap.values());

        result.forEach(resulta -> {
            resulta.setServiceType("Alojamiento");
        });
        return result;
    }

    /**
     * Completed reservations
     *   
    */
    public List<CaregiverBookingsDto> getCompletedBookings(int userId) {
        List<CaregiverBookingsDto> result = new ArrayList<CaregiverBookingsDto>();
    
        List<OwnerReservations> reservations = faBoardingReservationDao.showCompletedBookings(userId);

        Map<Integer, CaregiverBookingsDto> reservationMap = new HashMap<>();
        for (OwnerReservations reservation : reservations) {
            if(reservationMap.containsKey(reservation.getBoardingReservationId())) {
                // agregar el perro a lo que ya tienes
                CaregiverBookingsDto reservationDto = reservationMap.get(reservation.getBoardingReservationId());
                reservationDto.getDogs().add(new DogSimpleDto(reservation.getDogId(), reservation.getDogName()));
            } else {
                // crear el dto y agregarlo al map
                CaregiverBookingsDto res = new CaregiverBookingsDto();
                res.setBoardingReservationId(reservation.getBoardingReservationId());
                res.setCaregiverName(reservation.getCaregiverName());
                res.setDate(reservation.getDate());
                res.setReservationStatus(reservation.getReservationStatus());
                res.setDogs(new ArrayList<>());
    
                // crear el perro
                DogSimpleDto dog = new DogSimpleDto();
                dog.setDogId(reservation.getDogId());
                dog.setName(reservation.getDogName());
    
                // agregar el perro al dto
                res.getDogs().add(dog);
    
                reservationMap.put(reservation.getBoardingReservationId(), res);
            }   
        }
    
        result.addAll(reservationMap.values());

        result.forEach(resulta -> {
            resulta.setServiceType("Alojamiento");
        });
        return result;
    }

    /**
     * Reservation info
     *   
    */
    public ReservationInfoDto getBookingInfo(int boardingReservationId) {
        ReservationInfoDto result = new ReservationInfoDto();
    
        ReservationInfoEntity reservations = faBoardingReservationDao.showBookingInfo(boardingReservationId);
    
        Map<Integer, ReservationInfoDto> reservationMap = new HashMap<>();

        if(reservationMap.containsKey(reservations.getBoardingReservationId())) {
                // agregar el perro a lo que ya tienes
                ReservationInfoDto reservationInfoDto = reservationMap.get(reservations.getBoardingReservationId());
                reservationInfoDto.getDogs().add(new DogDto(reservations.getDogId(), reservations.getDogName(), reservations.getBreed(), reservations.getSize(), reservations.getBirthDate(), false, false, null));
            } else {
                // crear el dto y agregarlo al map
                ReservationInfoDto res = new ReservationInfoDto();
                res.setBoardingReservationId(reservations.getBoardingReservationId());
                res.setStartDate(reservations.getStartingDate());
                res.setEndDate(reservations.getEndingDate());
                res.setIncludePickup(reservations.getIncludePickup());
                res.setLocation(reservations.getLocation());
                res.setNotes(reservations.getNotes());
                res.setNightlyRate(reservations.getNightlyRate());
                res.setPickupRate(reservations.getPickupRate());

                res.setDogs(new ArrayList<>());
    
                // crear el perro
                DogDto dog = new DogDto();
                dog.setUserId(reservations.getDogId());
                dog.setName(reservations.getDogName());
                dog.setBreed(reservations.getBreed());  
                dog.setSize(reservations.getSize());
                dog.setBirthDate(reservations.getBirthDate());
    
                // agregar el perro al dto
                res.getDogs().add(dog);
    
                reservationMap.put(reservations.getBoardingReservationId(), res);
            }   
        
        
        result = reservationMap.get(boardingReservationId);
    
        result.setServiceType("Alojamiento");

        return result;
    }
}
