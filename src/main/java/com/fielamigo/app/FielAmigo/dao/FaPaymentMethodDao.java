package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.PaymentMethodResDto;
import com.fielamigo.app.FielAmigo.entity.FaPaymentMethod;

@Component
public interface FaPaymentMethodDao {
    @Insert("""
        INSERT INTO FA_PAYMENT_METHOD (
            USER_ID, NAME, LAST_DIGITS, CAT_CARD_TYPE, NUMBERS,
            EXPIRATION_DATE, STATUS, TX_HOST, TX_USER, TX_DATE
        ) VALUES (
            #{userId}, #{name}, #{lastDigits}, 346, #{numbers},
            #{expirationDate}, 1, 'localhost', 'admin', NOW()
        )
            """)
    public void addPaymentMethod(FaPaymentMethod faPaymentMethod);

    @Select("""
        SELECT
            PAYMENT_METHOD_ID,
            LAST_DIGITS,
            EXPIRATION_DATE,
            NAME
        FROM FA_PAYMENT_METHOD PM
        INNER JOIN FA_USER USR
            ON PM.USER_ID = USR.USER_ID
        WHERE USR.USER_ID = #{userId}
        AND USR.STATUS = 1
        AND USR.CAT_STATUS IN (1, 2)
        AND PM.STATUS = 1
            """)
    public List<PaymentMethodResDto> getPaymentMethods(int userId);
}
