package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface FaImageDao {
    @Select("""
        INSERT INTO FA_IMAGE
            (URL, TX_HOST, TX_USER, TX_DATE)
        VALUES
            (#{url}, 'localhost', 'admin', NOW())
        RETURNING IMAGE_ID
            """)
    public int addImage(String url);
}
