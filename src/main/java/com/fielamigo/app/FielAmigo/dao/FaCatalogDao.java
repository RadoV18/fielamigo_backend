package com.fielamigo.app.FielAmigo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.fielamigo.app.FielAmigo.dto.CatalogDto;

@Component
public interface FaCatalogDao {
    
    @Select("""
        SELECT
            CAT.CATALOG_ID AS id,
            CAT.NAME
        FROM FA_CATALOG CAT
        INNER JOIN FA_CATALOG_TYPE CAT_TYPE
            ON CAT.CATALOG_TYPE_ID = CAT_TYPE.CATALOG_TYPE_ID
        WHERE
            CAT_TYPE.TYPE = #{catalogType}
            AND CAT_TYPE.STATUS = 1
            AND CAT.STATUS = 1
            """)
    List<CatalogDto> getData(String catalogType);

    @Select("""
        SELECT
            CAT.CATALOG_ID AS id,
            CAT.NAME
        FROM FA_CATALOG CAT
        INNER JOIN FA_CATALOG_HIERARCHY CAT_HIER
            ON CAT.CATALOG_ID = CAT_HIER.CHILD_CATALOG_ID
        INNER JOIN FA_CATALOG CAT2
            ON CAT_HIER.PARENT_CATALOG_ID = CAT2.CATALOG_ID
        WHERE CAT2.CATALOG_ID = #{catalogId}
            """)
    List<CatalogDto> getCities(int countryId);
}
