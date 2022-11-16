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
import com.fielamigo.app.FielAmigo.bl.UserBl;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApi {
    
    private MailBl mailBl;
    private UserBl userBl;

    @Autowired
    public AuthApi(MailBl mailBl, UserBl userBl) {
        this.mailBl = mailBl;
        this.userBl = userBl;
    }

    /**
     * Endpoint to verify the code sent to the user's email.
     * @param verificationCodeReqDto the request body.
     * @return A code that indicates if the verification was successful.
     */
    @PostMapping("/verification-code")
    public ResponseEntity<ResponseDto<VerificationCodeResDto>>
        verifyCode(@RequestBody VerificationCodeReqDto verificationCodeReqDto) {

        try {
            // validate data
            verificationCodeReqDto.validate();

            // verify if the code is correct and get the user's id
            int userId = mailBl.verifyCode(verificationCodeReqDto);

            if(userId != -1) {
                // update user's status to active
                userBl.setToActiveWithIncompleteData(userId);

                // return OK
                VerificationCodeResDto verificationCodeResDto = new VerificationCodeResDto("OK");
                ResponseDto<VerificationCodeResDto> responseDto =
                    new ResponseDto<>(verificationCodeResDto, null, true);
                
                return new ResponseEntity<>(responseDto, HttpStatus.OK);
            } else {
                // the code is incorrect
                ResponseDto<VerificationCodeResDto> responseDto =
                    new ResponseDto<>(null, "The code is incorrect", false);
                return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
            }
        } catch (FielAmigoException e) {
            ResponseDto<VerificationCodeResDto> responseDto =
                new ResponseDto<>(null, e.getMessage(), false);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }

}
