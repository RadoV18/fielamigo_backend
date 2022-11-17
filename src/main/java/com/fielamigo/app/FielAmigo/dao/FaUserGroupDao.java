package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Insert;

public interface FaUserGroupDao {
    @Insert("""
        INSERT INTO FA_USER_GROUP (
            USER_ID, GROUP_ID, STATUS, TX_HOST, TX_USER, TX_DATE
        ) VALUES (
            #{userId}, #{groupId}, 1, 'anonymous', 'localhost', NOW()
        )
            """)
    public void addUserToGroup(int userId, int groupId);
}
