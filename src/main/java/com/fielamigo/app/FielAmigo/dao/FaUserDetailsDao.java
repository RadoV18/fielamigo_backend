package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Select;

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
            AND USR.CAT_STATUS = 1
            AND USR_DET.STATUS = 1
            """)
    FaUserDetails findUserDetailsByEmail(String email);
}
