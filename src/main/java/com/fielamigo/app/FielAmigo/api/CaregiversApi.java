package com.fielamigo.app.FielAmigo.api;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fielamigo.app.FielAmigo.bl.BoardingBl;
import com.fielamigo.app.FielAmigo.bl.CaregiverBl;
import com.fielamigo.app.FielAmigo.dto.CaregiverBoardingReqDto;
import com.fielamigo.app.FielAmigo.dto.CaregiverCardDto;
import com.fielamigo.app.FielAmigo.dto.CaregiverServiceDto;
import com.fielamigo.app.FielAmigo.dto.CaregiverBookedDateDto;
import com.fielamigo.app.FielAmigo.dto.ResponseDto;
import com.fielamigo.app.FielAmigo.utils.AuthUtil;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;
import com.fielamigo.app.FielAmigo.utils.JwtUtil;
import com.fielamigo.app.FielAmigo.utils.UnauthorizedException;

@RestController
@RequestMapping("/api/v1/caregivers")
public class CaregiversApi {

    private BoardingBl boardingBl;
    private CaregiverBl caregiverBl;

    public CaregiversApi(BoardingBl boardingBl, CaregiverBl caregiverBl) {
        this.boardingBl = boardingBl;
        this.caregiverBl = caregiverBl;
    }

    /**
     * Endpoint to get all the caregivers that are available to board a pet.
     * @param startingDate the start date of the boarding.
     * @param endingDate the end date of the boarding.
     * @param dogCount the number of dogs to board.
     * @param cityId the city id.
     * @return A list of caregivers.
     */
    @GetMapping("/boarding")
    public ResponseEntity<ResponseDto<List<CaregiverCardDto>>> getCaregiversAvailableToBoard(
        @RequestHeader Map<String, String> headers,
        @RequestParam("starting") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startingDate,
        @RequestParam("ending") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endingDate,
        @RequestParam("cityId") Integer cityId,
        @RequestParam("dogCount") Integer dogCount
    ) {
        CaregiverBoardingReqDto req = new CaregiverBoardingReqDto(startingDate, endingDate, cityId, dogCount);
        ResponseDto<List<CaregiverCardDto>> responseDto = new ResponseDto<>(null, null, false);

        try {
            // TODO: validate data
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "SEARCH_BOARDING");
            
            // get the available caregivers
            List<Integer> caregivers = boardingBl.getAvailableCaregivers(req);

            // get the caregivers' info
            List<CaregiverCardDto> caregiversInfo = caregiverBl.getCaregiversInfo(caregivers);

            responseDto.setSuccessful(true);
            responseDto.setMessage(null);
            responseDto.setData(caregiversInfo);
            // set data
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
     * Endpoint to get a caregiver's bio by a caregiverId.
     * @param caregiverId the caregiver's id.
     * @return the caregiver's bio.
     */
    @GetMapping("/{caregiverId}/bio")
    public ResponseEntity<ResponseDto<String>> getCaregiverBio(
        @RequestHeader Map<String, String> headers,
        @PathVariable("caregiverId") Integer caregiverId
    ) {
        ResponseDto<String> responseDto = new ResponseDto<>(null, null, false);

        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "SEARCH_BOARDING");
            
            // get the caregiver's bio
            String bio = caregiverBl.getCaregiverBioById(caregiverId);

            responseDto.setSuccessful(true);
            responseDto.setMessage(null);
            responseDto.setData(bio);

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
     * Endpoint to get a caregiver's list of picture by a caregiverId.
     * @param caregiverId the caregiver's id.
     * @param headers the request headers.
     * @return the caregiver's list of pictures.
     */
    @GetMapping("/{caregiverId}/pictures")
    public ResponseEntity<ResponseDto<List<String>>> getCaregiverPictures(
        @RequestHeader Map<String, String> headers,
        @PathVariable("caregiverId") Integer caregiverId
    ) {
        ResponseDto<List<String>> responseDto = new ResponseDto<>(null, null, false);

        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "GET_PROFILE");
            
            // get the caregiver's pictures
            List<String> pictures = caregiverBl.getCaregiverPicturesById(caregiverId);

            responseDto.setSuccessful(true);
            responseDto.setMessage(null);
            responseDto.setData(pictures);

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
     * Endpoint to get a caregiver's list of experience details by a caregiverId.
     * @param caregiverId the caregiver's id.
     * @param headers the request headers.
     * @return the caregiver's list of experience details.
     */
    @GetMapping("/{caregiverId}/experience")
    public ResponseEntity<ResponseDto<List<String>>> getCaregiverExperience(
        @RequestHeader Map<String, String> headers,
        @PathVariable("caregiverId") Integer caregiverId
    ) {
        ResponseDto<List<String>> responseDto = new ResponseDto<>(null, null, false);

        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "SEARCH_BOARDING");
            
            // get the caregiver's experience
            List<String> experience = caregiverBl.getCaregiverExperienceById(caregiverId);

            responseDto.setSuccessful(true);
            responseDto.setMessage(null);
            responseDto.setData(experience);

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
     * Endpoint to get a caregiver's list of services by a caregiverId.
     * @param caregiverId the caregiver id.
     * @param headers the request headers.
     * @return A list of services.
     */
    @GetMapping("/{caregiverId}/services")
    public ResponseEntity<ResponseDto<List<CaregiverServiceDto>>> getCaregiverServices(
        @RequestHeader Map<String, String> headers,
        @PathVariable Integer caregiverId
    ) {
        ResponseDto<List<CaregiverServiceDto>> responseDto = new ResponseDto<>(null, null, false);

        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "GET_PROFILE");

            // get the caregiver's services
            List<CaregiverServiceDto> caregiversServices = caregiverBl.getCaregiverServices(caregiverId);

            responseDto.setSuccessful(true);
            responseDto.setMessage(null);
            responseDto.setData(caregiversServices);

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
     * Endpoint to get a caregiver's location by a caregiverId.
     */

    /**
     * Endpoint to get all the dates that a caregiver is not available to board a pet.
     * @param caregiverId the caregiver's id.
     * @param headers the request headers.
     * @param month the month to get the dates.
     * @param year the year to get the dates.
     * @return a list of dates.
     */
    @GetMapping("/{caregiverId}/boarding/booked-dates")
    public ResponseEntity<ResponseDto<CaregiverBookedDateDto>> getCaregiverUnavailableDates(
        @RequestHeader Map<String, String> headers,
        @PathVariable Integer caregiverId,
        @RequestParam Integer month,
        @RequestParam Integer year
    ) {
        ResponseDto<CaregiverBookedDateDto> responseDto = new ResponseDto<>(null, null, false);

        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "GET_PROFILE");

            // get the caregiver's unavailable dates
            CaregiverBookedDateDto unavailableDates =
                caregiverBl.getCaregiverUnavailableDates(caregiverId, year, month);

            responseDto.setSuccessful(true);
            responseDto.setMessage(null);
            responseDto.setData(unavailableDates);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }

}
