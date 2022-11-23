package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Insert("""
        INSERT INTO FA_CAREGIVER (USER_ID, TX_HOST, TX_USER, TX_DATE)
        VALUES (#{caregiverId}, 'localhost', 'admin', NOW())  
            """)
    public void create(int userId);
    
    @Update("""
        UPDATE FA_CAREGIVER
        SET BIO = #{bio}
        WHERE CAREGIVER_ID = #{caregiverId}
            """)
    public void updateCaregiverBio(int caregiverId, String bio);
}
