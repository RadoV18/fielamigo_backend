package com.fielamigo.app.FielAmigo.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.entity.AvailableCaregiver;
import com.fielamigo.app.FielAmigo.utils.Pair;

@Component
public interface AvailableBoardingCaregiverDao {
    @Select("""
        SELECT
            CRG.CAREGIVER_ID,
            BRD_RES.STARTING_DATE,
            BRD_RES.ENDING_DATE,
            BRD.MAX_DOGS,
            -- amount of dogs that are already booked
            COALESCE(COUNT(DOG_BRD.DOG_ID), 0) AS DOG_COUNT
        FROM FA_CAREGIVER CRG
        INNER JOIN FA_BOARDING_SERVICE BRD
            ON CRG.CAREGIVER_ID = BRD.CAREGIVER_ID
        INNER JOIN FA_BOARDING_RESERVATION BRD_RES
            ON BRD.BOARDING_SERVICE_ID = BRD_RES.BOARDING_SERVICE_ID
        INNER JOIN FA_DOG_BOARDING DOG_BRD
            ON BRD_RES.BOARDING_RESERVATION_ID = DOG_BRD.BOARDING_RESERVATION_ID
        -- city
        INNER JOIN FA_USER USR
            ON CRG.user_id = USR.user_id
        INNER JOIN FA_USER_ADDRESS USR_ADD
            ON USR.user_id = USR_ADD.user_id
        WHERE
            CRG.STATUS = 1
            AND BRD.STATUS = 1
            AND BRD_RES.STATUS = 1
            AND BRD_RES.CAT_STATUS = 5
            AND DOG_BRD.STATUS = 1
            AND (
                (BRD_RES.STARTING_DATE BETWEEN #{startingDate} AND #{endingDate}) OR
                (BRD_RES.ENDING_DATE BETWEEN #{startingDate} AND #{endingDate}) OR
                (BRD_RES.STARTING_DATE <= #{startingDate} AND BRD_RES.ENDING_DATE >= #{endingDate})
            )
            -- CITY
            AND USR.STATUS = 1
            AND USR_ADD.STATUS = 1
            AND USR_ADD.CAT_CITY_ID = #{cityId}
            -- NIGHTS
            AND BRD.MAX_NIGHTS >= #{nights}
        GROUP BY CRG.CAREGIVER_ID, BRD_RES.STARTING_DATE, BRD_RES.ENDING_DATE, BRD.MAX_DOGS
        ORDER BY CRG.CAREGIVER_ID, STARTING_DATE, ENDING_DATE
            """)
    public List<AvailableCaregiver> getBookedCaregivers(Date startingDate,
        Date endingDate, int cityId, int nights);

    @Select("""
        SELECT
            CRG.CAREGIVER_ID,
            BRD.MAX_DOGS
        FROM FA_CAREGIVER CRG
        INNER JOIN FA_BOARDING_SERVICE BRD
            ON CRG.CAREGIVER_ID = BRD.CAREGIVER_ID
        INNER JOIN FA_USER USR
            ON CRG.USER_ID = USR.USER_ID
        INNER JOIN FA_USER_ADDRESS USR_ADD
            ON USR.USER_ID = USR_ADD.USER_ID
        WHERE
            CRG.STATUS = 1
            AND BRD.STATUS = 1
            AND USR.STATUS = 1
            AND USR_ADD.STATUS = 1
            AND USR_ADD.CAT_CITY_ID = #{cityId}
            AND BRD.MAX_NIGHTS >= #{nights}
            AND BRD.MAX_DOGS >= #{dogCount}
            """)
    public List<Pair<Integer, Integer>> getPossibleCaregivers(Integer dogCount,
        Integer nights, Integer cityId);
}
