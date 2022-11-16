package com.fielamigo.app.FielAmigo.dto;

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

    @Override
    public String toString() {
        return "AuthReqDto [email=" + email + ", password=" + password + "]";
    }

}
