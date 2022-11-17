package com.fielamigo.app.FielAmigo.utils;

import java.util.regex.Pattern;

public class Validation {
    
    public static void isEmailValid(String email) throws FielAmigoException {
        if (email == null || email.isEmpty()) {
            throw new FielAmigoException("Email is null or empty");
        }
        // check email with regex
        // OWASP email regex
        String regexPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean isEmailValid = Pattern.compile(regexPattern)
            .matcher(email)
            .matches();
        if(!isEmailValid) {
            throw new FielAmigoException("Email is not valid");
        }
    }

    public static void isPasswordValid(String password) throws FielAmigoException {
        if (password == null || password.isEmpty()) {
            throw new FielAmigoException("Password is null or empty");
        }
    }
}
