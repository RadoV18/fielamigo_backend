package com.fielamigo.app.FielAmigo.dto;

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

    @Override
    public String toString() {
        return "VerificationCodeReqDto [cookie=" + cookie + ", code=" + code + "]";
    }

}
