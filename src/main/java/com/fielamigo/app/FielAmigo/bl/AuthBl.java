package com.fielamigo.app.FielAmigo.bl;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.utils.FielAmigoException;

@Service
public class AuthBl {

    public AuthBl() {
    }

    /**
     * Checks if the password is a common password.
     * @param password
     * @return true if the password is a common password, false otherwise.
     */
    public void isCommonPassword(String password) throws FielAmigoException {
        // TODO: implement
    }
}
