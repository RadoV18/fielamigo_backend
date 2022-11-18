package com.fielamigo.app.FielAmigo.api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fielamigo.app.FielAmigo.bl.AuthBl;
import com.fielamigo.app.FielAmigo.bl.MailBl;
import com.fielamigo.app.FielAmigo.bl.UserBl;
import com.fielamigo.app.FielAmigo.dto.CreateUserDto;
import com.fielamigo.app.FielAmigo.dto.MailVerificationDto;
import com.fielamigo.app.FielAmigo.dto.ResponseDto;
import com.fielamigo.app.FielAmigo.dto.UserAddressReqDto;
import com.fielamigo.app.FielAmigo.utils.AuthUtil;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;
import com.fielamigo.app.FielAmigo.utils.JwtUtil;
import com.fielamigo.app.FielAmigo.utils.UnauthorizedException;

@RestController
@RequestMapping("/api/v1/users")
public class UsersApi {
    
    private AuthBl authBl;
    private UserBl userBl;
    private MailBl mailBl;

    public UsersApi(AuthBl authBl, UserBl userBl, MailBl mailBl) {
        this.authBl = authBl;
        this.userBl = userBl;
        this.mailBl = mailBl;
    }

    /**
     * Endpoint to sign-up.
     * @param createUserDto the request body.
     * @return A cookie to verify the user later.
     */
    @PostMapping("")
    public ResponseEntity<ResponseDto<MailVerificationDto>>
        createUser(@RequestBody CreateUserDto createUserDto) {
        // create user
        try {
            // validate data
            createUserDto.validate();
            userBl.userExists(createUserDto.getEmail());
            authBl.isCommonPassword(createUserDto.getPassword());
            
            // Create a new user
            int userId = userBl.createUser(createUserDto);
            // Generate a verification code and send it to the user.
            MailVerificationDto cookie = mailBl.addVerificationCode(createUserDto, userId);
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

    /**
     * Endpoint to add user details.
     */

    /**
     * Endpoint to add address details.
     */
    @PostMapping("/address")
    public ResponseEntity<ResponseDto<UserAddressReqDto>> addAddress(
        @RequestHeader Map<String, String> headers,
        @RequestBody UserAddressReqDto userAddressReqDto
    ) {
        ResponseDto<UserAddressReqDto> responseDto =
            new ResponseDto<>(null, null, false);
        try {
            // TODO: validate data
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "ADD_PAYMENT_METHOD");
            // get the user id from the token
            int userId = JwtUtil.getUserIdFromToken(jwt);

            // add the address
            userBl.addAddress(userId, userAddressReqDto);

            responseDto.setSuccessful(true);
            responseDto.setMessage("Address added successfully");
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
            
        } catch (FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }
}
