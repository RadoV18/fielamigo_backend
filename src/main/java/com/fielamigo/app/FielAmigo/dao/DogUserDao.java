package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.entity.DogUser;

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
            CB.NAME AS BREED
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
        WHERE
            D.USER_ID = #{userId}
            AND USR.CAT_STATUS IN (1, 2)
            AND USR.STATUS = 1
            AND D.STATUS = 1
            AND CB.STATUS = 1
            AND CS.STATUS = 1
            AND DI.STATUS = 1
            AND IM.STATUS = 1
            """)
    public List<DogUser> getDogs(int userId);
}
