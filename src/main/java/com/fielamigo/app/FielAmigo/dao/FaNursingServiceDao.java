package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.CaregiverServiceDto;

@Component
public interface FaNursingServiceDao {
    @Select("""
        SELECT
            NRS.NURSING_SERVICE_ID AS SERVICE_ID,
            NRS.HOURLY_RATE AS PRICE
        FROM FA_NURSING_SERVICE NRS
        INNER JOIN FA_CAREGIVER CRG
            ON NRS.CAREGIVER_ID = CRG.CAREGIVER_ID
        WHERE
            NRS.CAREGIVER_ID = #{caregiverId}
            AND NRS.STATUS = 1
            AND CRG.STATUS = 1
            """)
    public CaregiverServiceDto getService(int caregiverId);
}
