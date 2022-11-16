package com.fielamigo.app.FielAmigo.bl;

import java.util.HashMap;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import com.fielamigo.app.FielAmigo.dto.VerificationCodeReqDto;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;
import com.fielamigo.app.FielAmigo.utils.Pair;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dto.CreateUserDto;
import com.fielamigo.app.FielAmigo.dto.MailVerificationDto;

@Service
public class MailBl {
    private static final HashMap<String, Pair<Integer, Integer>> verificationCodes =
        new HashMap<String, Pair<Integer, Integer>>();

    private final JavaMailSender mailSender;

    public MailBl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends a verification code to the user and stores it in a HashMap, so it
     * can be verified later.
     * @param user the user to send the verification code to.
     * @param userId the user's id.
     * @return
     */
    public MailVerificationDto addVerificationCode(CreateUserDto user, int userId) {
        // Generate random UUID to be used as a cookie.
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        // Generate random verification code.
        int code = (int) (Math.random() * 9000) + 1000;
        Pair<Integer, Integer> pair = new Pair<Integer, Integer>(userId, code);

        // Store the verification code in the HashMap.
        verificationCodes.put(randomUUIDString, pair);

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

    /**
     * Verifies the user's verification code.
     * @param mailVerificationDto the user's cookie and verification code.
     * @return the user's id if the code is correct, -1 if the code is incorrect
     * @throws FielAmigoException if the cookie is not in the HashMap.
     */
    public int verifyCode(VerificationCodeReqDto mailVerificationDto)
            throws FielAmigoException{
        // Cookie sent by the user.
        String cookie = mailVerificationDto.getCookie();
        int code = mailVerificationDto.getCode();

        // Check if the cookie is valid and get the user's id.
        if(verificationCodes.containsKey(cookie)) {
            Pair<Integer, Integer> data = verificationCodes.get(cookie);
            if(data.getSecond() == code) {
                // return the user's id to update the user's status.
                return data.getFirst();
            } else {
                return -1;
            }
        } else {
            throw new FielAmigoException("Invalid cookie");
        }
    }
}
