package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.entity.FaUser;

@Component
public interface FaUserDao {

    @Insert("""
        INSERT INTO
            FA_USER (EMAIL, SECRET, CAT_STATUS, STATUS, TX_USER, TX_HOST, TX_DATE)
        VALUES
            (#{email}, #{secret}, #{catStatus}, #{status}, 'anonymous', 'localhost', NOW())
            """)
    void createUser(FaUser createUserDto);
}
