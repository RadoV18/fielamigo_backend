package com.fielamigo.app.FielAmigo.dao;


import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.entity.FaUser;

@Component
public interface FaUserDao {

    @Select("""
        INSERT INTO
            FA_USER (EMAIL, SECRET, CAT_STATUS, STATUS, TX_USER, TX_HOST, TX_DATE)
        VALUES
            (#{email}, #{secret}, 3, #{status}, 'anonymous', 'localhost', NOW())
        RETURNING USER_ID
            """)
    int createUser(FaUser createUserDto);

    @Update("""
        UPDATE FA_USER
        SET 
            CAT_STATUS = 2
        WHERE
            USER_ID = #{userId}
            """)
    void setToActiveWithIncompleteData(int userId);

    @Select("""
        SELECT
            COUNT(*) AS COUNT
        FROM
            FA_USER
        WHERE
            EMAIL = #{email}
        """
    )
    int userExists(String email);
}
