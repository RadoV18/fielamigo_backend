package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.entity.FaRole;

@Component
public interface FaRoleDao {

    @Select("""
        SELECT
            RL.NAME
        FROM
            FA_USER USR
        INNER JOIN FA_USER_GROUP USR_GRP
            ON USR.USER_ID = USR_GRP.USER_ID
        INNER JOIN FA_GROUP GRP
            ON USR_GRP.GROUP_ID = GRP.GROUP_ID
        INNER JOIN FA_ROLE_GROUP RL_GRP
            ON GRP.GROUP_ID = RL_GRP.GROUP_ID
        INNER JOIN FA_ROLE RL
            ON RL_GRP.ROLE_ID = RL.ROLE_ID
        WHERE
            USR.EMAIL = #{email}
            AND USR.STATUS = 1
            AND USR.CAT_STATUS IN (1, 2)
            AND USR_GRP.STATUS = 1
            AND GRP.STATUS = 1
            AND RL_GRP.STATUS = 1
            AND RL.STATUS = 1
        """)
    public List<FaRole> getRolesByEmail(String email);
}
