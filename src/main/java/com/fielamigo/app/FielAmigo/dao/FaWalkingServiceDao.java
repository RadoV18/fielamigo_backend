package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.CaregiverServiceDto;

@Component
public interface FaWalkingServiceDao {
    @Select("""
        SELECT
            WALK.WALKING_SERVICE_ID AS SERVICE_ID,
            WALK.HOURLY_RATE AS PRICE
        FROM FA_WALKING_SERVICE WALK
        INNER JOIN FA_CAREGIVER CRG
            ON WALK.CAREGIVER_ID = CRG.CAREGIVER_ID
        WHERE
            WALK.CAREGIVER_ID = #{caregiverId}
            AND WALK.STATUS = 1
            AND CRG.STATUS = 1
            """)
    public CaregiverServiceDto getService(int caregiverId);
}
