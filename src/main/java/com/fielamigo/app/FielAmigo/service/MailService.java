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

    /**
     * Sends a boarding reservation confirmation email to the user.
     * @param userEmail the user's email.
     * @param reservationId the reservation id.
     * @param faBoardingReservation the reservation details.
     * @param dogs the dogs in the reservation.
     */
    public void sendBoardingReqConfirmation(
        String userEmail, Integer reservationId, String startingDay, String startingDate,
        String endingDay, String endingDate, String dogString, String notes, double nightlyRate,
        int nights, double subTotal, double pickupRate, double total
    ) throws Exception;

    /**
     * Sends a boarding reservation request email to the caregiver.
     * @param caregiverEmail the caregiver's email.
     * @param reservationId the reservation id.
     * @param faBoardingReservation the reservation details.
     * @param dogs the dogs in the reservation.
     */
    public void sendNewBoardingRequest(
        String caregiverEmail, Integer reservationId, String startingDay, String startingDate,
        String endingDay, String endingDate, String dogString, String notes, double nightlyRate,
        int nights, double subTotal, double pickupRate, double total
    ) throws Exception;

}
