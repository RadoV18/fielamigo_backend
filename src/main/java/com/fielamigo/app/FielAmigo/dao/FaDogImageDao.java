package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

@Component
public interface FaDogImageDao {
    @Insert("""
        INSERT INTO FA_DOG_IMAGE
            (DOG_ID, IMAGE_ID, TX_HOST, TX_USER, TX_DATE)
        VALUES
            (#{dogId}, #{imageId}, 'localhost', 'admin', NOW())
            """)
    public void addDogImage(int dogId, int imageId);
}
