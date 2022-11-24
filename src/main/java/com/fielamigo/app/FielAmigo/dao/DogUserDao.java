package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.DogUserDto;

@Component
public interface DogUserDao {

    @Select("""
        SELECT
            D.DOG_ID,
            D.USER_ID,
            D.NAME,
            D.IS_MALE,
            D.BIRTH_DATE,
            CS.NAME AS SIZE,
            CB.NAME AS BREED,
            IM.URL AS IMAGE_URL
        FROM FA_DOG D
        INNER JOIN FA_USER USR
            ON D.USER_ID = USR.USER_ID
        INNER JOIN FA_CATALOG CB
            ON D.CAT_BREED = CB.CATALOG_ID
        INNER JOIN FA_CATALOG CS
            ON D.CAT_SIZE = CS.CATALOG_ID
        LEFT JOIN (
            SELECT
                DI.DOG_ID,
                IMG.URL
            FROM FA_DOG_IMAGE DI
            INNER JOIN FA_IMAGE IMG
                ON DI.IMAGE_ID = IMG.IMAGE_ID
            WHERE 
                DI.STATUS = 1
                AND IMG.STATUS = 1
        ) AS IM
        ON IM.DOG_ID = D.DOG_ID
        WHERE
            D.USER_ID = #{userId}
            AND USR.CAT_STATUS IN (1, 2)
            AND USR.STATUS = 1
            AND D.STATUS = 1
            AND CB.STATUS = 1
            AND CS.STATUS = 1
            """)
    public List<DogUserDto> getDogs(int userId);

    @Select("""
        SELECT
            D.DOG_ID,
            D.USER_ID,
            D.NAME,
            D.IS_MALE,
            D.BIRTH_DATE,
            CS.NAME AS SIZE,
            CB.NAME AS BREED,
            IM.URL AS IMAGE_URL
        FROM FA_DOG D
        INNER JOIN FA_USER USR
            ON D.USER_ID = USR.USER_ID
        INNER JOIN FA_CATALOG CB
            ON D.CAT_BREED = CB.CATALOG_ID
        INNER JOIN FA_CATALOG CS
            ON D.CAT_SIZE = CS.CATALOG_ID
        INNER JOIN FA_DOG_IMAGE DI
            ON D.DOG_ID = DI.DOG_ID
        INNER JOIN FA_IMAGE IM
            ON DI.IMAGE_ID = IM.IMAGE_ID
        INNER JOIN FA_CAREGIVER CRG
            ON CRG.USER_ID = USR.USER_ID
        WHERE
            CRG.CAREGIVER_ID = #{caregiverId}
            AND USR.CAT_STATUS IN (1, 2)
            AND USR.STATUS = 1
            AND D.STATUS = 1
            AND CB.STATUS = 1
            AND CS.STATUS = 1
            AND DI.STATUS = 1
            AND IM.STATUS = 1
            """)
    public List<DogUserDto> getDogsByCaregiverId(int caregiverId);
}
