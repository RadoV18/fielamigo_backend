package com.fielamigo.app.FielAmigo.api;

import com.fielamigo.app.FielAmigo.dto.ResponseDto;
import com.fielamigo.app.FielAmigo.dto.VerificationCodeReqDto;
import com.fielamigo.app.FielAmigo.dto.VerificationCodeResDto;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fielamigo.app.FielAmigo.bl.MailBl;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {
    
    private MailBl mailBl;

    @Autowired
    public AuthApi(MailBl mailBl) {
        this.mailBl = mailBl;
    }

    /**
     * Endpoint to verify the code sent to the user's email.
     * @param verificationCodeReqDto the request body.
     * @return A code that indicates if the verification was successful.
     */
    @PostMapping("/verification-code")
    public ResponseEntity<ResponseDto<VerificationCodeResDto>>
        verifyCode(@RequestBody VerificationCodeReqDto verificationCodeReqDto) {
        // validate data
        try {
            verificationCodeReqDto.validate();

            // verify code
            boolean isCodeValid = mailBl.verifyCode(verificationCodeReqDto);
            if(isCodeValid) {
                VerificationCodeResDto verificationCodeResDto = new VerificationCodeResDto("OK");
                ResponseDto<VerificationCodeResDto> responseDto =
                    new ResponseDto<>(verificationCodeResDto, null, true);
                
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (FielAmigoException e) {
            ResponseDto<VerificationCodeResDto> responseDto =
                new ResponseDto<>(null, e.getMessage(), false);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }

}
