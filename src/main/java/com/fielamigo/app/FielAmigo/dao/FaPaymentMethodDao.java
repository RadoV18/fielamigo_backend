package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

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
}
