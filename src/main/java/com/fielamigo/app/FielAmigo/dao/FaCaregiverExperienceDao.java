package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface FaCaregiverExperienceDao {
    @Select("""
        SELECT
            EXP.COMMENT
        FROM FA_CAREGIVER_EXPERIENCE EXP
        INNER JOIN FA_CAREGIVER CRG
            ON EXP.CAREGIVER_ID = CRG.CAREGIVER_ID
        WHERE
            EXP.CAREGIVER_ID = #{caregiverId}
            AND EXP.STATUS = 1
            AND CRG.STATUS = 1
            """)
    public List<String> getCaregiverExperience(int caregiverId);
}
