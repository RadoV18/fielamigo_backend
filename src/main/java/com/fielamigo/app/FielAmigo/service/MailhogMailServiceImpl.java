package com.fielamigo.app.FielAmigo.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dto.CreateUserDto;

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
    public void sendBoardingReqConfirmation(
        String userEmail, Integer reservationId, String startingDay, String startingDate,
        String endingDay, String endingDate, String dogString, String notes, double nightlyRate,
        int nights, double subTotal, double pickupRate, double total
    ) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setFrom("admin@fielamigo.com");
        helper.setTo(userEmail);
        helper.setSubject("Fiel Amigo - Reserva de Alojamiento solicitada");
        helper.setText("""
    <div>
        <style>
            body {
                font-family: Roboto;
                font-size: 14px;
                color: #333;
                line-height: 1.5;
            }
            .title{
                padding: 10px;
            }
            .title h1 {
                text-align: center;
            }
            .title .text {
                font-size: larger;
            }
            .container {
                width: 100%%;
                max-width: 600px;
                margin: 0 auto;
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            }
            .header {
                background-color: #01BF8F;
                color: #fff;
                padding: 10px;
                display: flex;
                flex-direction: row;
                justify-content: space-between;
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            }
            .header h1 {
                margin: 0;
            }
            .end{            
                display: flex;
                flex-direction: column;
                align-items: flex-end;
            }
            .card{
                display: flex;
                flex-direction: row;
                padding: 10px;
                border-radius: 3mm;
                border-color: #01BF8F;
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            }
            .card img {
                border-radius: 50%%;
                border-style: groove;
                border-color: #01BF8F;
                width: 70px;
                height: 70px;
            }
            .card .info {
                display: flex;
                flex-direction: column;
                justify-content: space-evenly;
                margin-left: 10px;
            }
            .card h2 {
                margin: 0;
            }
            .card h5 {
                margin: 0;
            }
            .check{
                margin: 1%%;
            }
            .notes{
                padding: 3%%;
                height: 100px;
            }
            .notes textarea {
                width: 100%%;
                height: 100%%;
                resize: none;
                border-radius: 3mm;
                border-color: #01BF8F;
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            }
            .sum{
                display: flex;
                flex-direction: row;
                justify-content: space-between;
            }
            .sum h4 {
                margin: 1%%;
            }
            .content {
                display: flex;
                flex-direction: column;
                padding: 10px;
            }
            .footer {
                background-color: #047B5B;
                color: #fff;
                padding: 10px;
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                display: flex;
                justify-content: center;
            }
        </style>
        <div class=\"container\">
            <div class=\"title\">
                <div>
                    <h1>Solicitud de reserva confirmada</h1>
                    <div class=\"text\">
                        <p>Se registró una solicitud de reserva con los siguientes datos:</p>
                        <p>La reserva se confirmará cuando el cuidador acepte la solicitud.</p>
                    </div>
                </div>
            </div>
            <div class=\"header\">
                <div>
                    <h1>%s,</h1>
                    <h1>%s</h1>
                </div>
                <div class=\"end\">
                    <h1>%s,</h1>
                    <h1>%s</h1>
                </div>
            </div>
            <div class=\"content\">
                <h1>Alojamiento para:</h1>
                %s 
                <div class=\"notes\">
                    <textarea readonly>%s</textarea>
                </div>
                <div>
                    <h2>Resumen</h2>
                    <div class=\"sum\">
                        <h4>Bs.%.2f x %d noches:</h4>
                        <h4>Bs.%.2f</h3>
                    </div>
                    <div class=\"sum\">
                        <h4>Recojo:</h4>
                        <h4>Bs.%.2f</h3>
                    </div>
                    <hr color=\"black\">
                    <div class=\"sum\">
                        <h4>Total:</h4>
                        <h4>Bs.%.2f</h4>
                    </div>
                </div>        
            </div>
            <div class=\"footer\">
                <p>FielAmigo - 2022</p>
            </div>
        </div>
    </div>
                """.formatted(
                    startingDay, startingDate, endingDay, endingDate,
                    dogString, notes, nightlyRate, nights, subTotal, pickupRate, total
                ), true);
        
        mailSender.send(message);
        
    }

    @Override
    public void sendNewBoardingRequest(
        String caregiverEmail, Integer reservationId, String startingDay, String startingDate,
        String endingDay, String endingDate, String dogString, String notes, double nightlyRate,
        int nights, double subTotal, double pickupRate, double total
    ) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("admin@fielamigo.com");
        helper.setTo(caregiverEmail);
        helper.setSubject("Fiel Amigo - Nueva Reserva de Alojamiento");
        helper.setText("""
        <div>
            <style>
                body {
                    font-family: Roboto;
                    font-size: 14px;
                    color: #333;
                    line-height: 1.5;
                }
                .title{
                    padding: 10px;
                }
                .title h1 {
                    text-align: center;
                }
                .title .text {
                    font-size: larger;
                }
                .container {
                    width: 100%%;
                    max-width: 600px;
                    margin: 0 auto;
                    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                }
                .header {
                    background-color: #01BF8F;
                    color: #fff;
                    padding: 10px;
                    display: flex;
                    flex-direction: row;
                    justify-content: space-between;
                    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                }
                .header h1 {
                    margin: 0;
                }
                .end{            
                    display: flex;
                    flex-direction: column;
                    align-items: flex-end;
                }
                .card{
                    display: flex;
                    flex-direction: row;
                    padding: 10px;
                    border-radius: 3mm;
                    border-color: #01BF8F;
                    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                }
                .card img {
                    border-radius: 50%%;
                    border-style: groove;
                    border-color: #01BF8F;
                    width: 70px;
                    height: 70px;
                }
                .card .info {
                    display: flex;
                    flex-direction: column;
                    justify-content: space-evenly;
                    margin-left: 10px;
                }
                .card h2 {
                    margin: 0;
                }
                .card h5 {
                    margin: 0;
                }
                .check{
                    margin: 1%%;
                }
                .notes{
                    padding: 3%%;
                    height: 100px;
                }
                .notes textarea {
                    width: 100%%;
                    height: 100%%;
                    resize: none;
                    border-radius: 3mm;
                    border-color: #01BF8F;
                    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                }
                .sum{
                    display: flex;
                    flex-direction: row;
                    justify-content: space-between;
                }
                .sum h4 {
                    margin: 1%%;
                }
                .content {
                    display: flex;
                    flex-direction: column;
                    padding: 10px;
                }
                .footer {
                    background-color: #047B5B;
                    color: #fff;
                    padding: 10px;
                    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                    display: flex;
                    justify-content: center;
                }
            </style>
            <div class=\"container\">
                <div class=\"title\">
                    <div>
                        <h1>Solicitud de Reserva</h1>
                        <div class=\"text\">
                            <p>Se registró una solicitud de reserva con los siguientes datos:</p>
                            <p>Ingresa a la aplicación para ver más detalles, y para aceptar o rechazar la solicitud.</p>
                        </div>
                    </div>
                </div>
                <div class=\"header\">
                    <div>
                        <h1>%s,</h1>
                        <h1>%s</h1>
                    </div>
                    <div class=\"end\">
                        <h1>%s,</h1>
                        <h1>%s</h1>
                    </div>
                </div>
                <div class=\"content\">
                    <h1>Alojamiento para:</h1>
                    %s
                    <div class=\"notes\">
                        <textarea readonly>%s</textarea>
                    </div>
                    <div>
                        <h2>Resumen</h2>
                        <div class=\"sum\">
                            <h4>Bs.%.2f x %d noches:</h4>
                            <h4>Bs.%.2f</h3>
                        </div>
                        <div class=\"sum\">
                            <h4>Recojo:</h4>
                            <h4>Bs.%.2f</h3>
                        </div>
                        <hr color=\"black\">
                        <div class=\"sum\">
                            <h4>Total:</h4>
                            <h4>Bs.%.2f</h4>
                        </div>
                    </div>        
                </div>
                <div class=\"footer\">
                    <p>FielAmigo - 2022</p>
                </div>
            </div>
        </div>
                """.formatted(
                    startingDay, startingDate, endingDay, endingDate,
                    dogString, notes, nightlyRate, nights, subTotal, pickupRate, total
                ), true);

        mailSender.send(message);
    }
}
