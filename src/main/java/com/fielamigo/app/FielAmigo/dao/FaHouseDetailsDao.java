package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface FaHouseDetailsDao {
    @Insert("""
    <script>
        INSERT INTO FA_HOUSE_DETAILS (
            CAREGIVER_ID, DETAIL, TX_HOST, TX_USER, TX_DATE
        ) VALUES
        <foreach collection="houseFeatures" item="houseFeature" separator=",">
            (#{caregiverId}, #{houseFeature}, 'localhost', 'admin', NOW())
        </foreach>
    </script>
            """)
    public void uploadHouseDetails(int caregiverId, List<String> houseFeatures);

    @Select("""
        SELECT
            DETAIL
        FROM FA_HOUSE_DETAILS
        WHERE CAREGIVER_ID = #{caregiverId}
            """)
    public List<String> getHouseDetails(int caregiverId);
}
