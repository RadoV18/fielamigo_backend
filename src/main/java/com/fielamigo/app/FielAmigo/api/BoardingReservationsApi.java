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

import com.fielamigo.app.FielAmigo.bl.BoardingBl;
import com.fielamigo.app.FielAmigo.bl.BoardingReservationBl;
import com.fielamigo.app.FielAmigo.dto.BoardingReservationReqDto;
import com.fielamigo.app.FielAmigo.dto.CaregiverBookingsDto;
import com.fielamigo.app.FielAmigo.dto.OwnerBookingsDto;
import com.fielamigo.app.FielAmigo.dto.ResponseDto;
import com.fielamigo.app.FielAmigo.utils.AuthUtil;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;
import com.fielamigo.app.FielAmigo.utils.JwtUtil;
import com.fielamigo.app.FielAmigo.utils.UnauthorizedException;

@RestController
@RequestMapping("/api/v1/boarding-reservations")
public class BoardingReservationsApi {

    private BoardingBl boardingBl;
    private BoardingReservationBl boardingReservationBl;
    private BoardingReservationBl boardingReservationOwnerBl;

    public BoardingReservationsApi(BoardingBl bookingBl, BoardingReservationBl boardingReservationBl,BoardingReservationBl boardingReservationOwnerBl) {
        this.boardingBl = bookingBl;
        this.boardingReservationBl = boardingReservationBl;
        this.boardingReservationOwnerBl = boardingReservationOwnerBl;
    }
    
    /**
     * Endpoint to make a boarding booking for a pet.
     */
    @PostMapping
    public ResponseEntity<ResponseDto<Void>> makeBooking(
        @RequestHeader Map<String, String> headers,
        @RequestBody BoardingReservationReqDto boardingReservationReqDto
    ) {
        ResponseDto<Void> responseDto = new ResponseDto<>(null, null, false);
        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "BOOK_BOARDING");
            // get the user id from the token
            int userId = JwtUtil.getUserIdFromToken(jwt);

            // TODO: validate data
            // validate dates
            // validate caregiver availability
            // validate payment method

            // TODO: validate user
            // validate if the pets exist
            // check if the dogs belong to the user

            // make the booking
            boardingBl.book(userId, boardingReservationReqDto);

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

    /* Show caregiver completed bookings */

    @GetMapping("/caregiver")
    public ResponseEntity<ResponseDto<List<CaregiverBookingsDto>>> showBookings(
        @RequestHeader Map<String, String> headers
    ) {
        ResponseDto<List<CaregiverBookingsDto>> responseDto = new ResponseDto<>(null, null, false);
        try {
            // check if the user has a token
            String token = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(token, "GET_CAREGIVER_BOOKINGS");
            // get the user id from the token
            int caregiverId = JwtUtil.getUserIdFromToken(token);

            // get the bookings
            List<CaregiverBookingsDto> bookings = boardingReservationBl.getBookings(caregiverId);

            responseDto.setData(bookings);
            responseDto.setSuccessful(true);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch(FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }
    }

    /* Show owner all bookings */
    
    @GetMapping("/owner")
    public ResponseEntity<ResponseDto<List<OwnerBookingsDto>>> showOwnerBookings(
        @RequestHeader Map<String, String> headers
    ) {
        ResponseDto<List<OwnerBookingsDto>> responseDto = new ResponseDto<>(null, null, false);
        try {
            // check if the user has a token
            String token = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(token, "GET_OWNER_BOOKINGS");
            // get the user id from the token
            int ownerId = JwtUtil.getUserIdFromToken(token);

            // get the bookings
            List<OwnerBookingsDto> bookings = boardingReservationOwnerBl.getOwnerBookings(ownerId);

            responseDto.setData(bookings);
            responseDto.setSuccessful(true);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch(FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }
    }
    
}
