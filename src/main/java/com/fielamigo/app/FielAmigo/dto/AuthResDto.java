package com.fielamigo.app.FielAmigo.dto;

public class AuthResDto {
    private String token;
    private String refreshToken;

    public AuthResDto() {
    }

    public AuthResDto(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "AuthResDto [refreshToken=" + refreshToken + ", token=" + token + "]";
    }
}
