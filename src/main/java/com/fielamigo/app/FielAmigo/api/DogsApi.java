package com.fielamigo.app.FielAmigo.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fielamigo.app.FielAmigo.bl.DogBl;
import com.fielamigo.app.FielAmigo.dto.DogDto;
import com.fielamigo.app.FielAmigo.dto.ResponseDto;
import com.fielamigo.app.FielAmigo.entity.DogUser;
import com.fielamigo.app.FielAmigo.utils.AuthUtil;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;
import com.fielamigo.app.FielAmigo.utils.JwtUtil;
import com.fielamigo.app.FielAmigo.utils.UnauthorizedException;

@RestController
@RequestMapping("/api/v1/dogs")
public class DogsApi {

    private DogBl dogBl;

    public DogsApi(DogBl dogBl) {
        this.dogBl = dogBl;
    }

    /**
     * Endpoint to get all the user's dogs by a userId, the user has to be logged in.
     * @param userId the id of the user
     * @return a list of pets
     */
    @GetMapping
    public ResponseEntity<ResponseDto<List<DogUser>>> getDogsByUserId(
        @RequestHeader Map<String, String> headers) {
        ResponseDto<List<DogUser>> responseDto =
            new ResponseDto<>(null, null, false);

        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "GET_DOGS");
            // get the user id from the token
            int userId = JwtUtil.getUserIdFromToken(jwt);

            // get the user's dogs
            List<DogUser> dogs = dogBl.getDogs(userId);

            responseDto.setData(dogs);
            responseDto.setSuccessful(true);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);

        } catch (FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }
    
    /**
     * Endpoint to create a new dog, the user has to be logged in.
     * @param PetReqDto the request body
     */
    @PostMapping
    public ResponseEntity<ResponseDto<DogUser>> addDog(
        @RequestHeader Map<String, String> headers, @RequestBody DogDto dogDto) {
        ResponseDto<DogUser> responseDto =
            new ResponseDto<>(null, null, false);

        try {
            // TODO: validate the request body

            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "ADD_DOG");
            // get the user id from the token
            int userId = JwtUtil.getUserIdFromToken(jwt);
            // check if the userId of the token is the same as the userId in the request body
            if (userId != dogDto.getUserId()) {
                throw new FielAmigoException("The userId in the token is not the same as the userId in the request body");
            }

            // create the dog
            dogBl.addDog(dogDto);

            responseDto.setSuccessful(true);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

        } catch (FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }  
}
