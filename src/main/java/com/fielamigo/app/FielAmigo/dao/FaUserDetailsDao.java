package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.fielamigo.app.FielAmigo.dto.UserDetailsResDto;
import com.fielamigo.app.FielAmigo.entity.FaUserDetails;

public interface FaUserDetailsDao {
    @Select("""
        SELECT
            USR_DET.FIRST_NAME,
            USR_DET.LAST_NAME
        FROM FA_USER_DETAILS USR_DET
        INNER JOIN FA_USER USR
            ON USR_DET.USER_ID = USR.USER_ID
        WHERE
            USR.EMAIL = #{email}
            AND USR.STATUS = 1
            AND USR.CAT_STATUS IN (1, 2)
            AND USR_DET.STATUS = 1
            """)
    public FaUserDetails findUserDetailsByEmail(String email);

    @Insert("""
        INSERT INTO FA_USER_DETAILS
            (
                USER_ID, FIRST_NAME, LAST_NAME, PHONE_NUMBER, BIRTH_DATE, STATUS,
                TX_HOST, TX_USER, TX_DATE
            )
        VALUES (
            #{userId}, #{firstName}, #{lastName}, #{phoneNumber}, #{birthDate}, 1,
            'localhost', 'admin', NOW()
        )
            """)
    public void addUserDetails(FaUserDetails faUserDetails);

    @Select("""
        SELECT
            USR_DET.FIRST_NAME,
            USR_DET.LAST_NAME,
            USR_DET.PHONE_NUMBER,
            USR_DET.BIRTH_DATE,
            IMAGE.IMAGE_URL
        FROM FA_USER_DETAILS USR_DET
        INNER JOIN FA_USER USR
            ON USR_DET.USER_ID = USR.USER_ID
        LEFT JOIN (
            SELECT
                USR_IMG.USER_ID,
                IMG.URL AS IMAGE_URL
            FROM FA_IMAGE IMG
            INNER JOIN FA_USER_IMAGE USR_IMG
                ON USR_IMG.IMAGE_ID = IMG.IMAGE_ID
            WHERE
                USR_IMG.USER_ID = #{userId}
                AND USR_IMG.STATUS = 1
                AND IMG.STATUS = 1
        ) AS IMAGE
        ON IMAGE.USER_ID = USR.USER_ID
        WHERE
            USR.USER_ID = #{userId}
            AND USR.STATUS = 1
            AND USR.CAT_STATUS = 2
            AND USR_DET.STATUS = 1
            """)
    public UserDetailsResDto getUserDetailsById(int userId);
}
