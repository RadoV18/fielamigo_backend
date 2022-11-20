package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface FaCaregiverDao {
    @Select("""
        SELECT
            CRG.BIO
        FROM FA_CAREGIVER CRG
        WHERE
            CRG.CAREGIVER_ID = #{caregiverId}
            AND CRG.STATUS = 1
            """)
    public String getCaregiverBio(int caregiverId);
}
