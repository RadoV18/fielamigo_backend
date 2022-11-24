package com.fielamigo.app.FielAmigo.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import com.fielamigo.app.FielAmigo.dto.CaregiverInfoDto;

@Component
public interface CaregiverInfoDao {

    @Select("""
        select 
            fi.url as imageUrl,
            (ud.first_name || ' ' || ud.last_name) as caregiverName, 
            fr.rating as rating, count(fr.comments) as comments, 
            (fua.zone || ', ' || cat.name) as location
            from fa_user u
            inner join fa_user_image fui on fui.user_id = u.user_id
            inner join fa_image fi on fi.image_id = fui.image_id 
            inner join FA_user_details ud on ud.user_id = u.user_id
            inner join fa_review fr on fr.caregiver_id  = u.user_id
            inner join fa_user_address fua on fua.user_id = u.user_id
            inner join fa_caregiver fc on fc.user_id  = u.user_id
            inner join fa_catalog cat on cat.catalog_id  = fua.cat_city_id 
            where fc.caregiver_id = 1 and cat.catalog_type_id = 5
            group by fi.url, ud.first_name, ud.last_name, fr.rating, cat.name, fua.zone;      
        """)
    public CaregiverInfoDto caregiverInfoDto(Integer caregiverId);
}
