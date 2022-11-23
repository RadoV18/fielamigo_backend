package com.fielamigo.app.FielAmigo.bl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.FaCommonPasswordsDao;
import com.fielamigo.app.FielAmigo.dao.FaRoleDao;
import com.fielamigo.app.FielAmigo.dao.FaUserDao;
import com.fielamigo.app.FielAmigo.dao.FaUserDetailsDao;
import com.fielamigo.app.FielAmigo.dto.AuthReqDto;
import com.fielamigo.app.FielAmigo.dto.AuthResDto;
import com.fielamigo.app.FielAmigo.entity.FaRole;
import com.fielamigo.app.FielAmigo.entity.FaUserDetails;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;
import com.fielamigo.app.FielAmigo.utils.JwtUtil;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class AuthBl {

    private FaUserDao faUserDao;
    private FaRoleDao faRoleDao;
    private FaUserDetailsDao faUserDetailsDao;
    private FaCommonPasswordsDao faCommonPasswordsDao;

    public AuthBl(FaUserDao faUserDao, FaRoleDao faRoleDao,
        FaUserDetailsDao faUserDetailsDao, FaCommonPasswordsDao faCommonPasswordsDao
    ) {
        this.faUserDao = faUserDao;
        this.faRoleDao = faRoleDao;
        this.faUserDetailsDao = faUserDetailsDao;
        this.faCommonPasswordsDao = faCommonPasswordsDao;
    }

    /**
     * Method to authenticate a user.
     * @param credentials the user's email and password.
     * @return the token and refresh token.
     * @throws FielAmigoException if the user is not found or the password is incorrect.
     */
    public AuthResDto authenticate(AuthReqDto credentials) throws FielAmigoException{
        AuthResDto result = new AuthResDto();

        // get the user's hashed password from the database
        String currentHashedPassword = faUserDao.findSecretByEmail(credentials.getEmail());

        // check if the password was found
        if(currentHashedPassword != null) {
            // check if the password is correct
            BCrypt.Result bcryptResult = BCrypt.verifyer()
                .verify(credentials.getPassword().toCharArray(), currentHashedPassword);

            if(bcryptResult.verified) {
                // password is correct

                // get user data for the token
                int userId = faUserDao.findIdByEmail(credentials.getEmail());
                FaUserDetails user = faUserDetailsDao.findUserDetailsByEmail(credentials.getEmail());

                // get user roles
                List<FaRole> roles = faRoleDao.getRolesByEmail(credentials.getEmail());
                // cast roles to a string array
                List<String> rolesAsString = new ArrayList<>();
                for(FaRole role : roles) {
                    rolesAsString.add(role.getName());
                }

                // check if the user is an owner
                Integer id = faUserDao.checkIfCaregiver(userId);
                int caregiverId = id == null ? -1 : id;

                boolean isOwner = true;
                if(caregiverId != -1) {
                    isOwner = false;
                }

                // generate token and refresh token.
                String token = JwtUtil.generateToken(userId, caregiverId, user,
                    rolesAsString, 30000, isOwner);
                String refreshToken = JwtUtil.generateToken(userId, caregiverId,
                    user, rolesAsString, 50000, isOwner);

                // set the result
                result.setToken(token);
                result.setRefreshToken(refreshToken);
            } else {
                // password is incorrect
                throw new FielAmigoException("Incorrect password");
            }
        } else {
            // user not found
            throw new FielAmigoException("User not found");
        }

        return result;
    }

    /**
     * Checks if the password is a common password.
     * @param password
     * @return true if the password is a common password, false otherwise.
     */
    public void isCommonPassword(String password) throws FielAmigoException {
        int result = faCommonPasswordsDao.checkPassword(password);
        if(result > 0) {
            throw new FielAmigoException("Password is too common");
        }
    }
}
