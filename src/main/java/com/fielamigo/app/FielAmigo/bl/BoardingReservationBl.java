package com.fielamigo.app.FielAmigo.bl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fielamigo.app.FielAmigo.dao.FaBoardingReservationDao;
import com.fielamigo.app.FielAmigo.dto.CaregiverBookingsDto;
import com.fielamigo.app.FielAmigo.dto.OwnerBookingsDto;


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

    /**
     * @param ownerId
     * @return bookings
     */
    public List<OwnerBookingsDto> getOwnerBookings(int ownerId) {
        final List<OwnerBookingsDto> bookings = faBoardingReservationDao.showOwnerBookings(ownerId);
        bookings.forEach(booking -> {
            booking.setServiceType("Alojamiento");
        });
        return bookings;
    }
}
