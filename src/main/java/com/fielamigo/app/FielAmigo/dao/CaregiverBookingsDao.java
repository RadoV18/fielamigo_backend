package com.fielamigo.app.FielAmigo.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.entity.CaregiverBookings;

@Component
public interface CaregiverBookingsDao {
    @Select("""
        SELECT
            BRD_RES.STARTING_DATE,
            BRD_RES.ENDING_DATE,
            COUNT(DOG_BRD.DOG_ID) AS DOG_COUNT
        FROM FA_BOARDING_RESERVATION BRD_RES
        INNER JOIN FA_BOARDING_SERVICE BRD
            ON BRD_RES.BOARDING_SERVICE_ID = BRD.BOARDING_SERVICE_ID
        INNER JOIN FA_DOG_BOARDING DOG_BRD
            ON BRD_RES.BOARDING_RESERVATION_ID = DOG_BRD.BOARDING_RESERVATION_ID
        WHERE
            BRD_RES.STATUS = 1
            AND BRD.STATUS = 1
            AND DOG_BRD.STATUS = 1
            AND BRD.CAREGIVER_ID = #{caregiverId}
            AND (
                (BRD_RES.STARTING_DATE BETWEEN #{startingDate} AND #{endingDate}) OR
                (BRD_RES.ENDING_DATE BETWEEN #{startingDate} AND #{endingDate}) OR
                (BRD_RES.STARTING_DATE <= #{startingDate} AND BRD_RES.ENDING_DATE >= #{endingDate})
            )
        GROUP BY BRD_RES.STARTING_DATE, BRD_RES.ENDING_DATE
            """)
    public List<CaregiverBookings> getCaregiverBookedDates(Integer caregiverId, Date startingDate, Date endingDate);
}
