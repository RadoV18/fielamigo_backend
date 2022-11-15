package com.fielamigo.app.FielAmigo.bl;

import java.util.HashMap;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import com.fielamigo.app.FielAmigo.dto.VerificationCodeReqDto;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dto.CreateUserDto;
import com.fielamigo.app.FielAmigo.dto.MailVerificationDto;

@Service
public class MailBl {
    private static final HashMap<String, Integer> verificationCodes =
        new HashMap<String, Integer>();

    private final JavaMailSender mailSender;

    public MailBl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a verification code to the user and stores it in a HashMap, so it
     * can be verified later.
     * @param user 
     * @return
     */
    public MailVerificationDto addVerificationCode(CreateUserDto user) {
        // Generate random UUID to be used as a cookie.
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        // Generate random verification code.
        int code = (int) (Math.random() * 9000) + 1000;
        
        // Store the verification code in the HashMap.
        verificationCodes.put(randomUUIDString, code);

        // Send the verification code to the user asynchronously.
        Thread mailThread = new Thread(() -> {
            try {
                this.sendMail(user, code);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        mailThread.start();

        // Return the UUID to be used as a cookie, so the user can verify the code later.
        return new MailVerificationDto(randomUUIDString);
    }

    /**
     * Sends a verification code to the user.
     * @param userDto the user to send the verification code to.
     * @param code the verification code.
     * @throws Exception
     */
    public void sendMail(CreateUserDto userDto, int code) throws Exception {
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

    public boolean verifyCode(VerificationCodeReqDto mailVerificationDto) {
        String cookie = mailVerificationDto.getCookie();
        int code = verificationCodes.get(mailVerificationDto.getCookie());
        if(verificationCodes.containsKey(cookie)) {
            if(verificationCodes.get(cookie) == code) {
                System.out.println("Code is valid");
                return true;
            }
        }
        System.out.println("Code is invalid");
        return false;
    }
}
