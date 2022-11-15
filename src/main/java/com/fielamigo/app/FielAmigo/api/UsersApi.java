package com.fielamigo.app.FielAmigo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fielamigo.app.FielAmigo.bl.MailBl;
import com.fielamigo.app.FielAmigo.bl.UserBl;
import com.fielamigo.app.FielAmigo.dto.CreateUserDto;
import com.fielamigo.app.FielAmigo.dto.MailVerificationDto;
import com.fielamigo.app.FielAmigo.dto.ResponseDto;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;

@RestController
@RequestMapping("/api/v1/users")
public class UsersApi {
    
    private UserBl userBl;
    private MailBl mailBl;

    public UsersApi(UserBl userBl, MailBl mailBl) {
        this.userBl = userBl;
        this.mailBl = mailBl;
    }

    /**
     * Endpoint to sign-up.
     * @param userDto the request body.
     * @return A cookie to verify the user later.
     */
    @PostMapping("")
    public ResponseEntity<ResponseDto<MailVerificationDto>> createUser(@RequestBody CreateUserDto userDto) {
        // TODO: validate userDto
        try {
            // Create a new user
            userBl.createUser(userDto);
            // Generate a verification code and send it to the user.
            MailVerificationDto cookie = mailBl.addVerificationCode(userDto);
            // Return the cookie to the user.
            ResponseDto<MailVerificationDto> responseDto =
                new ResponseDto<>(cookie, null, true);

            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (FielAmigoException e) {
            ResponseDto<MailVerificationDto> responseDto =
                new ResponseDto<>(null, e.getMessage(), false);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }
}
