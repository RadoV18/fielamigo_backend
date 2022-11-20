package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.CaregiverCardDto;

@Component
public interface CaregiverCardDao {

    
    @Select("""
        <script>
        SELECT
        CRG.CAREGIVER_ID AS CAREGIVER_ID, IMAGE.URL AS IMAGE_URL,
        USR_DET.FIRST_NAME, USR_DET.LAST_NAME,
        COALESCE(RES.RESERVATION_COUNT, 0),
        COALESCE(AVG(REV.RATING), 0) AS RATING,
        COALESCE(COUNT(REV.REVIEW_ID), 0) AS REVIEW_COUNT,
        CAT_C.NAME AS CITY, USR_ADD.ZONE, BRD.NIGHTLY_RATE AS PRICE
        FROM FA_CAREGIVER CRG
        INNER JOIN FA_USER USR
            ON CRG.USER_ID = USR.USER_ID
        -- IMAGE
        LEFT JOIN(
            SELECT
                USR_IMG.USER_ID,
                IMG.URL
            FROM FA_USER_IMAGE USR_IMG
            INNER JOIN FA_IMAGE IMG
                ON USR_IMG.IMAGE_ID = IMG.IMAGE_ID
            WHERE USR_IMG.STATUS = 1
            AND IMG.STATUS = 1
        ) AS IMAGE
            ON IMAGE.USER_ID = USR.USER_ID
        -- REVIEWS
        LEFT JOIN FA_REVIEW REV
            ON REV.CAREGIVER_ID = USR.USER_ID
        -- ZONE AND CITY
        INNER JOIN FA_USER_ADDRESS USR_ADD
            ON USR.USER_ID = USR_ADD.USER_ID
        INNER JOIN FA_CATALOG CAT_C
            ON USR_ADD.cat_city_id = CAT_C.CATALOG_ID
        -- NAME
        INNER JOIN FA_USER_DETAILS USR_DET
            ON USR_DET.USER_ID = USR.USER_ID
        INNER JOIN FA_BOARDING_SERVICE BRD
            ON BRD.CAREGIVER_ID = CRG.CAREGIVER_ID
        -- RESERVATIONS
        LEFT JOIN (
            SELECT
                BRD_RES.BOARDING_SERVICE_ID,
                COUNT(*) AS RESERVATION_COUNT
            FROM FA_BOARDING_RESERVATION BRD_RES
            WHERE BRD_RES.STATUS = 1
            GROUP BY BOARDING_SERVICE_ID
            ) AS RES
            ON RES.BOARDING_SERVICE_ID = BRD.BOARDING_SERVICE_ID
        WHERE
            CRG.STATUS = 1
            AND USR.STATUS = 1
            AND USR.CAT_STATUS = 1
            AND USR_ADD.STATUS = 1
            AND CAT_C.STATUS = 1
            AND USR_DET.STATUS = 1
            AND BRD.STATUS = 1
            AND CRG.CAREGIVER_ID IN
            <foreach item="id" index="index" collection="caregivers" open="(" separator="," close=")">
                #{id}
            </foreach>    
        GROUP BY USR.USER_ID,
            CRG.CAREGIVER_ID,
            IMAGE_URL,
            USR_DET.FIRST_NAME,
            USR_DET.LAST_NAME,
            RES.RESERVATION_COUNT,
            CITY,
            USR_ADD.ZONE,
            PRICE;
        </script>""")
    public List<CaregiverCardDto> getCaregiversInfo(int[] caregivers);
}
