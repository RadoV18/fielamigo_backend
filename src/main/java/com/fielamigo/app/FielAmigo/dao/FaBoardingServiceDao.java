package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.CaregiverServiceDto;

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
}
