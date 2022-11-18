package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

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
}
