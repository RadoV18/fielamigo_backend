package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Insert;

import com.fielamigo.app.FielAmigo.entity.FaUserAddress;

public interface FaUserAddressDao {
    @Insert("""
        INSERT INTO FA_USER_ADDRESS (
            USER_ID, ADDRESS_1, ADDRESS_2, ZONE, CAT_COUNTRY_ID, CAT_CITY_ID,
            STATUS, TX_HOST, TX_USER, TX_DATE
        ) VALUES (
            #{userId}, #{address1}, #{address2}, #{zone}, #{catCountryId}, #{catCityId},
            1, 'localhost', 'admin', NOW()
        )
            """)
    public void addAddress(FaUserAddress faUserAddress);
}
