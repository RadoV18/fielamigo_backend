package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Insert;
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
    
}
