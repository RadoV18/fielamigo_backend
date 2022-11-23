package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.entity.FaUserImage;

@Component
public interface FaUserImageDao {
    @Insert("""
        INSERT INTO FA_USER_IMAGE (
            USER_ID, IMAGE_ID, STATUS, TX_HOST, TX_USER, TX_DATE
        ) VALUES (
            #{userId}, #{imageId}, 1, 'localhost', 'admin', NOW()
        )
            """)
    public void addUserImage(FaUserImage faUserImage);

    @Select("""
        SELECT
            IMAGE.URL
        FROM FA_USER_IMAGE USR_IMG
        INNER JOIN FA_IMAGE IMAGE
            ON USR_IMG.IMAGE_ID = IMAGE.IMAGE_ID
        WHERE USR_IMG.USER_ID = #{userId}
        AND USR_IMG.STATUS = 1
            """)
    public String getProfilePicture(int userId);
    
}
