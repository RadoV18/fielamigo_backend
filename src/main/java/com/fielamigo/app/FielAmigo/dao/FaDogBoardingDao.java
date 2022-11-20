package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.entity.FaDogBoarding;

@Component
public interface FaDogBoardingDao {
    @Insert("""
        INSERT INTO FA_DOG_BOARDING (
            BOARDING_RESERVATION_ID, DOG_ID, TX_HOST, TX_USER, TX_DATE
        ) VALUES (
            #{boardingReservationId}, #{dogId}, 'localhost', 'admin', NOW()
        )
            """)
    public void create(FaDogBoarding dogBoarding);
}
