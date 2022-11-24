package com.fielamigo.app.FielAmigo.bl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.fielamigo.app.FielAmigo.dao.FaBoardingReservationDao;
import com.fielamigo.app.FielAmigo.dto.CaregiverBookingsDto;
import com.fielamigo.app.FielAmigo.dto.DogSimpleDto;
import com.fielamigo.app.FielAmigo.dto.OwnerBookingsDto;
import com.fielamigo.app.FielAmigo.entity.OwnerReservations;


@Service
public class BoardingReservationBl {
    FaBoardingReservationDao faBoardingReservationDao;

    public BoardingReservationBl(FaBoardingReservationDao faBoardingReservationDao) {
        this.faBoardingReservationDao = faBoardingReservationDao;
    }

    /**
     * @param caregiverId
     * @return bookings
     */
    public List<CaregiverBookingsDto> getBookings(int caregiverId) {
        final List<CaregiverBookingsDto> bookings = faBoardingReservationDao.showBookings(caregiverId);
        bookings.forEach(booking -> {
            booking.setServiceType("Alojamiento");
        });
        return bookings;
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
}
