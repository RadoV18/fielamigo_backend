package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.ReviewResDto;

@Component
public interface FaReviewDao {
    @Select("""
        SELECT
            REV.REVIEW_ID,
            REV.RATING,
            REV.COMMENTS,
            USR_DET.FIRST_NAME,
            USR_DET.LAST_NAME
        FROM FA_REVIEW REV
        INNER JOIN FA_USER USR
            ON REV.CAREGIVER_ID = USR.USER_ID
        INNER JOIN FA_CAREGIVER CRG
            ON USR.USER_ID = CRG.USER_ID
        INNER JOIN FA_USER_DETAILS USR_DET
            ON USR.USER_ID = USR_DET.USER_ID
        WHERE
            REV.STATUS = 1
            AND USR.STATUS = 1
            AND CRG.STATUS = 1
            AND USR_DET.STATUS = 1
            AND CRG.CAREGIVER_ID = #{caregiverId}
        ORDER BY REV.REVIEW_ID DESC -- recent first
        LIMIT #{limit}
        OFFSET #{offset}
            """)
    List<ReviewResDto> getReviewsByCaregiverId(Integer caregiverId, Integer limit, Integer offset);

    @Select("""
        SELECT
            COUNT(REV.REVIEW_ID) AS REVIEW_COUNT
        FROM FA_REVIEW REV
        INNER JOIN FA_USER USR
            ON REV.CAREGIVER_ID = USR.USER_ID
        INNER JOIN FA_CAREGIVER CRG
            ON USR.USER_ID = CRG.USER_ID
        WHERE
            REV.STATUS = 1
            AND USR.STATUS = 1
            AND CRG.STATUS = 1
            AND CRG.CAREGIVER_ID = #{caregiverId}
            """)
    Integer getReviewCountByCaregiverId(Integer caregiverId);
}
