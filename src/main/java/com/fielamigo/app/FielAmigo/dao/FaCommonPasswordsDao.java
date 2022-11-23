package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Select;

public interface FaCommonPasswordsDao {

    @Select("""
        SELECT
            COALESCE(COUNT(*), 0)
        FROM FA_COMMON_PASSWORDS
        WHERE PASSWORD = #{password}
            """)
    int checkPassword(String password);
}
