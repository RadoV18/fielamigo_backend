package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.CaregiverBookingsDto;
import com.fielamigo.app.FielAmigo.dto.OwnerBookingsDto;
import com.fielamigo.app.FielAmigo.entity.FaBoardingReservation;

@Component
public interface FaBoardingReservationDao {
    @Select("""
        INSERT INTO FA_BOARDING_RESERVATION (
            BOARDING_SERVICE_ID, PAYPAL_ORDER_ID, STARTING_DATE, ENDING_DATE,
            NIGHTLY_RATE, PICKUP_RATE, INCLUDE_PICKUP, NOTES, CAT_STATUS, TX_HOST, TX_USER,
            TX_DATE
        ) VALUES (
            #{boardingServiceId}, #{paypalOrderId}, #{startingDate}, #{endingDate},
            #{nightlyRate}, #{pickupRate}, #{includePickup}, #{notes}, #{catStatus}, 'localhost', 'admin', NOW()
        )
        RETURNING BOARDING_RESERVATION_ID
            """)
    public Integer create(FaBoardingReservation reservation);

    @Select("""
        select d.name as dogName , (ud.first_name || ' ' || ud.last_name) as CaregiverName , (r.starting_date || ' - ' || r.ending_date) as date
            from FA_boarding_reservation r
            inner join FA_dog_boarding db on db.boarding_reservation_id = r.boarding_reservation_id
            inner join FA_dog d on d.dog_id = db.dog_id
            inner join Fa_user u on u.user_id = d.user_id
            inner join FA_user_details ud on ud.user_id = u.user_id
            inner join fa_boarding_service bs on bs.boarding_service_id = r.boarding_service_id
            inner join FA_caregiver c on bs.caregiver_id = c.caregiver_id
            where r.cat_status = 5
            and c.caregiver_id = #{caregiverId};
            """)
    public List<CaregiverBookingsDto> showBookings(int caregiverId);


    @Select("""
        select d.name as dogName , (ud.first_name || ' ' || ud.last_name) as CaregiverName , (r.starting_date || ' - ' || r.ending_date) as date, r.cat_status as status
            from FA_boarding_reservation r
            inner join FA_dog_boarding db on db.boarding_reservation_id = r.boarding_reservation_id
            inner join FA_dog d on d.dog_id = db.dog_id
            inner join Fa_user u on u.user_id = d.user_id
            inner join FA_user_details ud on ud.user_id = u.user_id
            inner join fa_boarding_service bs on bs.boarding_service_id = r.boarding_service_id
			left join fa_caregiver c on c.user_id  = u.user_id
			where u.user_id  = #{ownerId};
            """)
    public List<OwnerBookingsDto> showOwnerBookings(int ownerId);
}
