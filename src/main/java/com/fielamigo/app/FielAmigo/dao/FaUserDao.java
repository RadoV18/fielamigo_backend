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

    @Select("""
        SELECT
            SECRET
        FROM
            FA_USER
        WHERE
            EMAIL = #{email}
            AND STATUS = 1
            AND CAT_STATUS IN (1, 2)
            """)
    String findSecretByEmail(String email);

    @Select("""
        SELECT
            USER_ID
        FROM
            FA_USER
        WHERE
            EMAIL = #{email}
            AND STATUS = 1
            AND CAT_STATUS IN (1, 2)
            """)
    int findIdByEmail(String email);

    @Select("""
        SELECT
            EMAIL
        FROM FA_USER
        WHERE USER_ID = #{userId}
        AND STATUS = 1
            """)
    public String getUserEmail(int userId);

    @Select("""
        SELECT
            USR.EMAIL
        FROM FA_USER USR
        INNER JOIN FA_CAREGIVER CRG
            ON USR.USER_ID = CRG.USER_ID
        INNER JOIN FA_BOARDING_SERVICE BRD
            ON CRG.CAREGIVER_ID = BRD.CAREGIVER_ID
        WHERE
            BRD.BOARDING_SERVICE_ID = #{boardingServiceId}
            AND USR.STATUS = 1
            AND CRG.STATUS = 1
            AND BRD.STATUS = 1
            """)
    public String getCaregiverEmailFromBoardingServiceId(int serviceId);

    @Select("""
        SELECT
            COUNT(*) AS COUNT
        FROM FA_USER USR
        INNER JOIN FA_CAREGIVER CRG
            ON USR.USER_ID = CRG.USER_ID
        WHERE
            USR.USER_ID = #{userId}
            AND USR.STATUS = 1
            AND CRG.STATUS = 1
            """)
    public Integer checkIfOwner(Integer userId);
}
