package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.DogUserDto;
import com.fielamigo.app.FielAmigo.entity.FaDog;

@Component
public interface FaDogDao {

    @Select("""
        INSERT INTO FA_DOG (
            USER_ID, NAME, IS_MALE, BIRTH_DATE, CAT_SIZE, CAT_BREED, IS_STERILIZED,
            NOTES, STATUS, TX_HOST, TX_USER, TX_DATE
        ) VALUES (
            #{userId}, #{name}, #{isMale}, #{birthDate}, #{catSize}, #{catBreed}, #{isSterilized},
            #{notes}, 1, 'anonymous', 'localhost', NOW()
        )
        RETURNING DOG_ID
            """)
    public int addDog(FaDog dog);

    @Select("""
    <script>
        SELECT
            D.DOG_ID,
            D.USER_ID,
            D.NAME,
            D.IS_MALE,
            D.BIRTH_DATE,
            CS.NAME AS SIZE,
            CB.NAME AS BREED,
            IMAGE.IMAGE_URL
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
                IM.URL AS IMAGE_URL
            FROM FA_DOG_IMAGE DI
            INNER JOIN FA_IMAGE IM
                ON DI.IMAGE_ID = IM.IMAGE_ID
            WHERE
                DI.STATUS = 1
                AND IM.STATUS = 1
        ) AS IMAGE
        ON D.DOG_ID = IMAGE.DOG_ID
        WHERE
            USR.CAT_STATUS IN (1, 2)
            AND USR.STATUS = 1
            AND D.STATUS = 1
            AND CB.STATUS = 1
            AND CS.STATUS = 1
            AND D.DOG_ID IN
            <foreach item="item" index="index" collection="ids" open="(" separator="," close=")">
                #{item}
            </foreach>
    </script>
            """)
    public List<DogUserDto> getDogs(List<Integer> ids);
}
