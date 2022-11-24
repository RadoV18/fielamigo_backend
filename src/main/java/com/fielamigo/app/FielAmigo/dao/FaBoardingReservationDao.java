package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.entity.FaBoardingReservation;
import com.fielamigo.app.FielAmigo.entity.OwnerReservations;
import com.fielamigo.app.FielAmigo.entity.ReservationInfoEntity;

@Component
public interface FaBoardingReservationDao {
    @Select("""
        INSERT INTO FA_BOARDING_RESERVATION (
            BOARDING_SERVICE_ID, PAYPAL_ORDER_ID, STARTING_DATE, ENDING_DATE,
            NIGHTLY_RATE, PICKUP_RATE, INCLUDE_PICKUP, NOTES, CAT_STATUS, TX_HOST, TX_USER,
            TX_DATE
        ) VALUES (
            #{boardingServiceId}, #{paypalOrderId}, #{startingDate}, #{endingDate},
            #{nightlyRate}, #{pickupRate}, #{includePickup}, #{notes}, #{catStatus}, 'localhost', 'admin', NOW()
        )
        RETURNING BOARDING_RESERVATION_ID
            """)
    public Integer create(FaBoardingReservation reservation);

    /**
     * Accepted reservations
     * @param caregiverId
     * @return
      */
    @Select("""
        select 
            r.boarding_reservation_id as boardingReservationId,
            d.dog_id as dogId,
            d.name as dogName, 
            (ud.first_name || ' ' || ud.last_name) as CaregiverName , 
            (r.starting_date || ' - ' || r.ending_date) as date,
            r.cat_status as reservationStatus
                    from FA_boarding_reservation r
                    inner join FA_dog_boarding db on db.boarding_reservation_id = r.boarding_reservation_id
                    inner join FA_dog d on d.dog_id = db.dog_id
                    inner join Fa_user u on u.user_id = d.user_id
                    inner join FA_user_details ud on ud.user_id = u.user_id
                    inner join fa_boarding_service bs on bs.boarding_service_id = r.boarding_service_id
                    inner join FA_caregiver c on bs.caregiver_id = c.caregiver_id
                    where r.cat_status = 5
                    and c.caregiver_id = #{caregiverId};
            """)
    public List<OwnerReservations> showAcceptedBookings(int caregiverId);

    /**
     * New reservations
     * @param caregiverId
     * @return
      */
      @Select("""
        select 
            r.boarding_reservation_id as boardingReservationId,
            d.dog_id as dogId,
            d.name as dogName, 
            (ud.first_name || ' ' || ud.last_name) as CaregiverName , 
            (r.starting_date || ' - ' || r.ending_date) as date,
            r.cat_status as reservationStatus
                    from FA_boarding_reservation r
                    inner join FA_dog_boarding db on db.boarding_reservation_id = r.boarding_reservation_id
                    inner join FA_dog d on d.dog_id = db.dog_id
                    inner join Fa_user u on u.user_id = d.user_id
                    inner join FA_user_details ud on ud.user_id = u.user_id
                    inner join fa_boarding_service bs on bs.boarding_service_id = r.boarding_service_id
                    inner join FA_caregiver c on bs.caregiver_id = c.caregiver_id
                    where r.cat_status = 4
                    and c.caregiver_id = #{caregiverId};
        
            """)
    public List<OwnerReservations> showNewBookings(int caregiverId);

    /**
     * Completed reservations
     * @param caregiverId
     * @return
      */
      @Select("""
        select 
            r.boarding_reservation_id as boardingReservationId,
            d.dog_id as dogId,
            d.name as dogName, 
            (ud.first_name || ' ' || ud.last_name) as CaregiverName , 
            (r.starting_date || ' - ' || r.ending_date) as date,
            r.cat_status as reservationStatus
                    from FA_boarding_reservation r
                    inner join FA_dog_boarding db on db.boarding_reservation_id = r.boarding_reservation_id
                    inner join FA_dog d on d.dog_id = db.dog_id
                    inner join Fa_user u on u.user_id = d.user_id
                    inner join FA_user_details ud on ud.user_id = u.user_id
                    inner join fa_boarding_service bs on bs.boarding_service_id = r.boarding_service_id
                    inner join FA_caregiver c on bs.caregiver_id = c.caregiver_id
                    where r.cat_status = 347
                    and c.caregiver_id = #{caregiverId};
            """)
    public List<OwnerReservations> showCompletedBookings(int caregiverId);

    /**
     * Reservation Info
     * @param boardingReservationId
     * @return
      */
      @Select("""
            select 
                r.boarding_reservation_id  as boardingReservationId,
                d.dog_id as dogId,
                d.name as dogName, 
                d.cat_breed as breed, 
                d.cat_size as size, 
                d.birth_date as birthDate, 
                r.starting_date as startDate, 
                r.ending_date as endDate, 
                r.nightly_rate as nightlyRate, 
                r.include_pickup as includePickup, 
                r.pickup_rate as pickupRate, 
                r.notes as notes,
                (fua.address_1 || ', ' || fua.address_2) as location
                from FA_boarding_reservation r
                inner join FA_dog_boarding db on r.boarding_reservation_id = db.boarding_reservation_id
                inner join FA_dog d on db.dog_id = d.dog_id
                inner join fa_user fu on  fu.user_id  = d.user_id
                inner join fa_user_address fua on fua.user_id  = fu.user_id 
                inner join fa_boarding_service fbs on fbs.caregiver_id = 2
                where r.boarding_reservation_id = 1;
            """)
    public ReservationInfoEntity showBookingInfo(int boardingReservationId);

    /**
     * 
     * @param ownerId
     * @return
      */
    @Select("""
        SELECT BRD_RES.BOARDING_RESERVATION_ID as boardingReservationId ,
            D.dog_id as dogId,
            D.NAME AS dogName,
            (USR_DET_C.FIRST_NAME || ' ' || USR_DET_C.LAST_NAME) as caregiverName,
            (BRD_RES.STARTING_DATE || ' - ' || BRD_RES.ENDING_DATE) as date,
            R_ST.NAME AS reservationStatus
        FROM FA_BOARDING_RESERVATION BRD_RES
        -- dogs' names
        INNER JOIN FA_DOG_BOARDING D_BRD
            ON D_BRD.BOARDING_RESERVATION_ID = BRD_RES.BOARDING_RESERVATION_ID
        INNER JOIN FA_DOG D
            ON D_BRD.DOG_ID = D.DOG_ID
        INNER JOIN FA_USER USR
            ON D.USER_ID = USR.USER_ID
        -- caregiver's name
        INNER JOIN FA_BOARDING_SERVICE BRD_SRV
            ON BRD_SRV.BOARDING_SERVICE_ID = BRD_RES.BOARDING_SERVICE_ID
        INNER JOIN FA_CAREGIVER CRG
            ON CRG.CAREGIVER_ID = BRD_SRV.CAREGIVER_ID
        INNER JOIN FA_USER USR_C
            ON USR_C.USER_ID = CRG.USER_ID
        INNER JOIN FA_USER_DETAILS USR_DET_C
            ON USR_C.USER_ID = USR_DET_C.USER_ID
        -- current status
        INNER JOIN FA_CATALOG R_ST
            ON R_ST.CATALOG_ID = BRD_RES.CAT_STATUS
            where BRD_RES.STATUS = 1
            and D_BRD.STATUS = 1
            and D.STATUS = 1
            and USR.STATUS = 1
            and BRD_SRV.STATUS = 1
            and CRG.STATUS = 1
            and USR_C.STATUS = 1
            and USR_DET_C.STATUS = 1
            and R_ST.STATUS = 1
            and USR.USER_ID = #{ownerId};
            """)
    public List<OwnerReservations> showOwnerBookings(int ownerId);

    /**
     * Cancel reservation 
     */
    @Update("""
        update FA_boarding_reservation
            set cat_status = 6
            where boarding_reservation_id = #{boardingReservationId};
            """)
    public void CancelReservation(Integer boardingReservationId);
}



