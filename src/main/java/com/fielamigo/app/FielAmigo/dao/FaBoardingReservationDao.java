package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

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
}
