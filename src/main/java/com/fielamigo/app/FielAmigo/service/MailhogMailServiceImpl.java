package com.fielamigo.app.FielAmigo.service;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dto.CreateUserDto;
import com.fielamigo.app.FielAmigo.dto.DogUserDto;
import com.fielamigo.app.FielAmigo.entity.FaBoardingReservation;

@Service
public class MailhogMailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    public MailhogMailServiceImpl(){
    }

    public MailhogMailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a verification code to the user.
     * @param userDto the user to send the verification code to.
     * @param code the verification code.
     * @throws Exception
     */
    @Override
    public void sendVerificationCodeEmail(CreateUserDto userDto, int code) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(userDto.getEmail());
        helper.setFrom("admin@fielamigo.com");
        helper.setSubject("Fiel Amigo - Código de verificación");
        helper.setText("""
                <h1>Bienvenido a Fiel Amigo!</h1>
                <p>Tu código de verificación es: %d</p>
                """.formatted(code), true);

        mailSender.send(message);
    }

    @Override
    public void sendBoardingReqConfirmation(String userEmail, Integer reservationId,
            FaBoardingReservation faBoardingReservation, List<DogUserDto> dogs) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setFrom("admin@fielamigo.com");
        helper.setTo(userEmail);
        helper.setSubject("Fiel Amigo - Reserva de Alojamiento solicitada");
        helper.setText("""
                <h1>Reserva de alojamiento solicitada</h1>
                <p>Se ha solicitado una reserva de aljoamiento con los siguientes datos:</p>
                <p>Id de la reserva: %d</p>
                <p>Fecha de inicio: %s</p>
                <p>Fecha de fin: %s</p>
                <p>Perros: %s</p>
                """.formatted(reservationId, faBoardingReservation.getStartingDate(),
                faBoardingReservation.getEndingDate(), dogs), true);
        
        mailSender.send(message);
        
    }

    @Override
    public void sendNewBoardingRequest(String caregiverEmail, Integer reservationId,
            FaBoardingReservation faBoardingReservation, List<DogUserDto> dogs) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("admin@fielamigo.com");
        helper.setTo(caregiverEmail);
        helper.setSubject("Fiel Amigo - Nueva reserva de alojamiento");
        helper.setText("""
                <h1>Nueva reserva de alojamiento</h1>
                <p>Se ha solicitado una reserva de aljoamiento con los siguientes datos:</p>
                <p>Id de la reserva: %d</p>
                <p>Fecha de inicio: %s</p>
                <p>Fecha de fin: %s</p>
                <p>Perros: %s</p>
                """.formatted(reservationId, faBoardingReservation.getStartingDate(),
                faBoardingReservation.getEndingDate(), dogs), true);

        mailSender.send(message);
    }
}
