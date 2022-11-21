package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.CaregiverServiceDto;
import com.fielamigo.app.FielAmigo.entity.BoardingRate;
import com.fielamigo.app.FielAmigo.entity.FaBoardingService;

@Component
public interface FaBoardingServiceDao {
    @Select("""
        SELECT
            BRD.BOARDING_SERVICE_ID AS SERVICE_ID,
            BRD.NIGHTLY_RATE AS PRICE
        FROM FA_BOARDING_SERVICE BRD
        INNER JOIN FA_CAREGIVER CRG
            ON BRD.CAREGIVER_ID = CRG.CAREGIVER_ID
        WHERE
            BRD.CAREGIVER_ID = #{caregiverId}
            AND BRD.STATUS = 1
            AND CRG.STATUS = 1
            """)
    public CaregiverServiceDto getService(int caregiverId);

    @Select("""
        SELECT
            BRD.MAX_DOGS
        FROM FA_BOARDING_SERVICE BRD
        INNER JOIN FA_CAREGIVER CRG
            ON BRD.CAREGIVER_ID = CRG.CAREGIVER_ID
        WHERE
            BRD.CAREGIVER_ID = #{caregiverId}
            AND BRD.STATUS = 1
            AND CRG.STATUS = 1
            """)
    public Integer getMaxDogs(int caregiverId);

    @Select("""
        SELECT
            NIGHTLY_RATE,
            PICKUP_RATE
        FROM FA_BOARDING_SERVICE
        WHERE
            BOARDING_SERVICE_ID = #{boardingServiceId}
            AND STATUS = 1
            """)
    public BoardingRate getBoardingRate(Integer boardingServiceId);

    @Insert("""
        INSERT INTO FA_BOARDING_SERVICE(caregiver_id, nightly_rate, max_nights, max_dogs, can_pickup, pickup_rate, tx_host, tx_user, tx_date)
        VALUES(#{caregiverId}, #{nightlyRate}, #{maxNumberOfNights}, #{maxNumberOfDogs}, #{canPickup}, #{pickupRate}, 'localhost', 'anonymous', NOW())
            """)
    public void createBoardingService(FaBoardingService faBoardingService);
}
