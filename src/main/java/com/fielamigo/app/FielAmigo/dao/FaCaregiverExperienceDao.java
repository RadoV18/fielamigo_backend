package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
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

    @Insert("""
    <script>
        INSERT INTO FA_CAREGIVER_EXPERIENCE (
            CAREGIVER_ID, COMMENT, TX_HOST, TX_USER, TX_DATE
        ) VALUES
        <foreach collection="experienceList" item="experience" separator=",">
            (#{caregiverId}, #{experience}, 'localhost', 'admin', NOW())
        </foreach>
    </script>
            """)
    public void uploadCaregiverExperience(int caregiverId, List<String> experienceList);
}
