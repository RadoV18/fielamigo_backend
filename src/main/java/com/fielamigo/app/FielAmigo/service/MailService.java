package com.fielamigo.app.FielAmigo.service;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dto.CreateUserDto;

@Service
public interface MailService {

    /**
     * Sends a verification code to the user.
     * @param userDto the user to send the verification code to.
     * @param code the verification code.
     * @throws Exception
     */
    public void sendVerificationCodeEmail(CreateUserDto userDto, int code) throws Exception;

}
