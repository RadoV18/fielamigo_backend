package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface FaCaregiverImageDao {
    @Select("""
        SELECT
            IMG.URL AS URL
        FROM FA_IMAGE IMG
        INNER JOIN FA_CAREGIVER_IMAGE CRG_IMG
            ON IMG.IMAGE_ID = CRG_IMG.IMAGE_ID
        INNER JOIN FA_CAREGIVER CRG
            ON CRG_IMG.CAREGIVER_ID = CRG.CAREGIVER_ID
        WHERE
            CRG_IMG.CAREGIVER_ID = #{caregiverId}
            AND CRG_IMG.STATUS = 1
            AND IMG.STATUS = 1
            AND CRG.STATUS = 1
            """)
    public List<String> getCaregiverPictures(int caregiverId);

    @Insert("""
        INSERT INTO FA_CAREGIVER_IMAGE
            (CAREGIVER_ID, IMAGE_ID, TX_HOST, TX_USER, TX_DATE)
        VALUES
            (#{caregiverId}, #{imageId}, 'localhost', 'admin', NOW())
            """)
    public void uploadPicture(int caregiverId, int imageId);
}
