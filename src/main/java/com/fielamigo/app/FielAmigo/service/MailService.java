package com.fielamigo.app.FielAmigo.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dto.CreateUserDto;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a verification code to the user.
     * @param userDto the user to send the verification code to.
     * @param code the verification code.
     * @throws Exception
     */
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
}
