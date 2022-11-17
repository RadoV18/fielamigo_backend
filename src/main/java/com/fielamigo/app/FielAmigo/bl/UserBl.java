package com.fielamigo.app.FielAmigo.bl;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.FaUserDao;
import com.fielamigo.app.FielAmigo.dto.CreateUserDto;
import com.fielamigo.app.FielAmigo.entity.FaUser;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;

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
    public int createUser(CreateUserDto createUserDto) {
        FaUser faUser = new FaUser();
        faUser.setEmail(createUserDto.getEmail());

        // Encrypt password with BCrypt
        String secret = BCrypt
            .withDefaults()
            .hashToString(12, createUserDto.getPassword().toCharArray());
        faUser.setSecret(secret);
        System.out.println("secret: " + secret);

        // default values for status and cat_status
        faUser.setCatStatus(3);
        faUser.setStatus(1);

        // store user in database
        return this.faUserDao.createUser(faUser);
    }

    /**
     * Sets the status of a user to active with incomplete data after the user
     * sends the verification code.
     * @param userId
     */
    public void setToActiveWithIncompleteData(int userId) {
        this.faUserDao.setToActiveWithIncompleteData(userId);
    }

    /**
     * Checks if a user exists in the database.
     * @param email the email of the user to be checked.
     */
    public void userExists(String email) throws FielAmigoException {
        if(this.faUserDao.userExists(email) == 1) {
            throw new FielAmigoException("User already exists");
        }
    }

}
