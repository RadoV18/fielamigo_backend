package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.CaregiverServiceDto;

@Component
public interface FaTrainingServiceDao {
    @Select("""
        SELECT
            TRN.TRAINING_SERVICE_ID AS SERVICE_ID,
            TRN.HOURLY_RATE AS PRICE
        FROM FA_TRAINING_SERVICE TRN
        INNER JOIN FA_CAREGIVER CRG
            ON TRN.CAREGIVER_ID = CRG.CAREGIVER_ID
        WHERE
            TRN.CAREGIVER_ID = #{caregiverId}
            AND TRN.STATUS = 1
            AND CRG.STATUS = 1
            """)
    public CaregiverServiceDto getService(int caregiverId);
}
