package com.fielamigo.app.FielAmigo.bl;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.FaUserDao;
import com.fielamigo.app.FielAmigo.dto.CreateUserDto;
import com.fielamigo.app.FielAmigo.entity.FaUser;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UserBl {

    private FaUserDao faUserDao;
    
    public UserBl(FaUserDao faUserDao) {
        this.faUserDao = faUserDao;
    }

    /**
     * Stores a new user in the database.
     * @param createUserDto the user to be stored.
     */
    public void createUser(CreateUserDto createUserDto) {
        FaUser faUser = new FaUser();
        faUser.setEmail(createUserDto.getEmail());

        // Encrypt password with BCrypt
        String secret = BCrypt
            .withDefaults()
            .hashToString(15, createUserDto.getPassword().toCharArray());
        faUser.setSecret(secret);

        // default values for status and cat_status
        faUser.setCatStatus(1);
        faUser.setStatus(1);

        // store user in database
        this.faUserDao.createUser(faUser);
    }

}
