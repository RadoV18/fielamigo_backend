package com.fielamigo.app.FielAmigo.dto;

import com.fielamigo.app.FielAmigo.utils.FielAmigoException;

public class VerificationCodeReqDto {
    private String cookie;
    private int code;

    public VerificationCodeReqDto() {
    }

    public VerificationCodeReqDto(String cookie, int code) {
        this.cookie = cookie;
        this.code = code;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void validate() throws FielAmigoException {
        if (cookie == null || cookie.isEmpty()) {
            throw new FielAmigoException("Cookie is null or empty");
        }
        if (code < 1000 || code > 9999) {
            throw new FielAmigoException("Code is not valid");
        }
    }

    @Override
    public String toString() {
        return "VerificationCodeReqDto [cookie=" + cookie + ", code=" + code + "]";
    }

}
