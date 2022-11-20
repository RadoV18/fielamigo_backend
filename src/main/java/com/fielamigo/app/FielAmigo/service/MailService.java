package com.fielamigo.app.FielAmigo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dto.CreateUserDto;
import com.fielamigo.app.FielAmigo.dto.DogUserDto;
import com.fielamigo.app.FielAmigo.entity.FaBoardingReservation;

@Service
public interface MailService {

    /**
     * Sends a verification code to the user.
     * @param userDto the user to send the verification code to.
     * @param code the verification code.
     * @throws Exception
     */
    public void sendVerificationCodeEmail(CreateUserDto userDto, int code) throws Exception;

    /**
     * Sends a boarding reservation confirmation email to the user.
     * @param userEmail the user's email.
     * @param reservationId the reservation id.
     * @param faBoardingReservation the reservation details.
     * @param dogs the dogs in the reservation.
     */
    public void sendBoardingReqConfirmation(String userEmail, Integer reservationId,
        FaBoardingReservation faBoardingReservation, List<DogUserDto> dogs) throws Exception;

    /**
     * Sends a boarding reservation request email to the user.
     * @param caregiverEmail the caregiver's email.
     * @param reservationId the reservation id.
     * @param faBoardingReservation the reservation details.
     * @param dogs the dogs in the reservation.
     */
    public void sendNewBoardingRequest(String caregiverEmail, Integer reservationId,
        FaBoardingReservation faBoardingReservation, List<DogUserDto> dogs) throws Exception;

}
