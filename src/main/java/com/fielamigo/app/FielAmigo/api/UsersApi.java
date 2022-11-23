package com.fielamigo.app.FielAmigo.api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fielamigo.app.FielAmigo.bl.AuthBl;
import com.fielamigo.app.FielAmigo.bl.MailBl;
import com.fielamigo.app.FielAmigo.bl.UserBl;
import com.fielamigo.app.FielAmigo.dto.CreateUserDto;
import com.fielamigo.app.FielAmigo.dto.MailVerificationDto;
import com.fielamigo.app.FielAmigo.dto.ResponseDto;
import com.fielamigo.app.FielAmigo.dto.UserAddressReqDto;
import com.fielamigo.app.FielAmigo.dto.UserDetailsReqDto;
import com.fielamigo.app.FielAmigo.dto.UserDetailsResDto;
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
     * @param userDetailsReqDto the request body.
     * @param headers the request headers.
     * @param image the user's profile picture.
     */
    @PostMapping("/details")
    public ResponseEntity<ResponseDto<Void>> addUserDetails(
        @RequestHeader Map<String, String> headers,
        @RequestPart MultipartFile image,
        @RequestPart UserDetailsReqDto data
    ) {
        ResponseDto<Void> responseDto = new ResponseDto<>(null, null, false);
        try {
            // TODO: validate the data
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "ADD_PAYMENT_METHOD");
            // get the user id from the token
            int userId = JwtUtil.getUserIdFromToken(jwt);

            // add the user details
            userBl.addUserDetails(userId, data, image);

            responseDto.setSuccessful(true);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Endpoint to get a user's details.
     * @param headers the request headers.
     * @return the user's details.
     */
    @GetMapping("/details")
    public ResponseEntity<ResponseDto<UserDetailsResDto>> getUserDetails(
        @RequestHeader Map<String, String> headers
    ) {
        // thread sleep
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ResponseDto<UserDetailsResDto> responseDto = new ResponseDto<>(null, null, false);
        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "ADD_PAYMENT_METHOD");
            // get the user id from the token
            int userId = JwtUtil.getUserIdFromToken(jwt);
            System.out.println(userId);
            // get the user details
            UserDetailsResDto userDetails = userBl.getUserDetails(userId);
            System.out.println(userDetails);
            // if the user has no details, return a 404
            if(userDetails == null) {
                responseDto.setSuccessful(false);
                responseDto.setMessage("User details not found");
                return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
            }

            responseDto.setSuccessful(true);
            responseDto.setData(userDetails);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Endpoint to add address details.
     * @param userAddressReqDto the request body.
     * @param headers the request headers.
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

    /**
     * Endpoint to get a user's profile picture
     * @param headers the request headers.
     * @return the user's profile picture url.
     */
    @GetMapping("/{userId}/profile-picture")
    public ResponseEntity<ResponseDto<String>> getProfilePicture(
        @RequestHeader Map<String, String> headers,
        @PathVariable int userId
    ) {
        ResponseDto<String> responseDto = new ResponseDto<>(null, null, false);
        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "GET_PROFILE");

            // get the user's profile picture
            String profilePicture = userBl.getProfilePicture(userId);

            if(profilePicture == null) {
                responseDto.setSuccessful(false);
                responseDto.setMessage("Profile picture not found");
                return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
            }

            responseDto.setSuccessful(true);
            responseDto.setData(profilePicture);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }

    // TODO: get current user's address
}
