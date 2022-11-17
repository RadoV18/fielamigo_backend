package com.fielamigo.app.FielAmigo.dto;

import com.fielamigo.app.FielAmigo.utils.FielAmigoException;
import com.fielamigo.app.FielAmigo.utils.Validation;

public class AuthReqDto {
    private String email;
    private String password;

    public AuthReqDto() {
    }

    public AuthReqDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void validate() throws FielAmigoException {
        Validation.isEmailValid(email);
        Validation.isPasswordValid(password);
    }

    @Override
    public String toString() {
        return "AuthReqDto [email=" + email + ", password=" + password + "]";
    }

}
