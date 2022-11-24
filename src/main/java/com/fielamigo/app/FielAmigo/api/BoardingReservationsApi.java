package com.fielamigo.app.FielAmigo.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.fielamigo.app.FielAmigo.dto.ReservationInfoDto;
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
    private BoardingReservationBl boardingReservationInfoBl;

    public BoardingReservationsApi(BoardingBl bookingBl, BoardingReservationBl boardingReservationBl,BoardingReservationBl boardingReservationOwnerBl,BoardingReservationBl boardingReservationInfoBl) {
        this.boardingBl = bookingBl;
        this.boardingReservationBl = boardingReservationBl;
        this.boardingReservationOwnerBl = boardingReservationOwnerBl;
        this.boardingReservationInfoBl = boardingReservationInfoBl;
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

    /**
     * 
     *  Endpoint to show caregiver accepted bookings 
     */

    @GetMapping("/caregiver/accepted")
    public ResponseEntity<ResponseDto<List<CaregiverBookingsDto>>> showAcceptedBookings(
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
            List<CaregiverBookingsDto> bookings = boardingReservationBl.getAcceptedBookings(caregiverId);

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


    /**
     * 
     *  Endpoint to show caregiver new bookings 
     */

    @GetMapping("/caregiver/new")
    public ResponseEntity<ResponseDto<List<CaregiverBookingsDto>>> showNewBookings(
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
            List<CaregiverBookingsDto> bookings = boardingReservationBl.getNewBookings(caregiverId);

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
    
    /**
     * 
     *  Endpoint to show caregiver completed bookings 
     */

    @GetMapping("/caregiver/completed")
    public ResponseEntity<ResponseDto<List<CaregiverBookingsDto>>> showCompletedBookings(
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
            List<CaregiverBookingsDto> bookings = boardingReservationBl.getCompletedBookings(caregiverId);

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

    /**
     * 
     *  Endpoint to show caregiver completed bookings 
     */
    
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
    
    /** 
     * Endpoint to cancel a reservation (update bdd)
     */
    @GetMapping("/owner/cancel/{boardingReservationId}")
    public ResponseEntity<ResponseDto<Void>> cancelBooking(
        @RequestHeader Map<String, String> headers,
        @PathVariable int boardingReservationId
    ) {
        ResponseDto<Void> responseDto = new ResponseDto<>(null, null, false);
        try {
            // check if the user has a token
            String token = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(token, "GET_OWNER_BOOKINGS");
            
            // cancel the booking
            boardingReservationOwnerBl.cancelReservation(boardingReservationId);

            responseDto.setSuccessful(true);
            responseDto.setMessage("Reserva cancelada");            
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch(FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }
    }

    /** 
     * Endpoint to complete a reservation (update bdd)
     */
    @GetMapping("/caregiver/toComplete/{boardingReservationId}")
    public ResponseEntity<ResponseDto<Void>> completingReservation(
        @RequestHeader Map<String, String> headers,
        @PathVariable int boardingReservationId
    ) {
        ResponseDto<Void> responseDto = new ResponseDto<>(null, null, false);
        try {
            // check if the user has a token
            String token = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(token, "UPDATE_BOARDING");
            
            // cancel the booking
            boardingReservationOwnerBl.completingReservation(boardingReservationId);

            responseDto.setSuccessful(true);
            responseDto.setMessage("Reserva completada");            
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch(FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }
    }
    
    /** 
     * Endpoint to accept a reservation (update bdd)
     */
    @GetMapping("/caregiver/accept/{boardingReservationId}")
    public ResponseEntity<ResponseDto<Void>> acceptingReservation(
        @RequestHeader Map<String, String> headers,
        @PathVariable int boardingReservationId
    ) {
        ResponseDto<Void> responseDto = new ResponseDto<>(null, null, false);
        try {
            // check if the user has a token
            String token = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(token, "UPDATE_BOARDING");
            
            // cancel the booking
            boardingReservationOwnerBl.acceptingReservation(boardingReservationId);

            responseDto.setSuccessful(true);
            responseDto.setMessage("Reserva aceptada");            
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch(FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }
    }

    /** 
     * Endpoint to show a reservation info 
     */
    @GetMapping("/caregiver/booking/info/{boardingReservationId}")
    public ResponseEntity<ResponseDto<ReservationInfoDto>> reservationInfo(
        @RequestHeader Map<String, String> headers,
        @PathVariable int boardingReservationId
    ) {
        ResponseDto<ReservationInfoDto> responseDto = new ResponseDto<>(null, null, false);
        try {
            // check if the user has a token
            String token = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(token, "GET_CAREGIVER_BOOKINGS");
            // get the user id from the token

            ReservationInfoDto booking = boardingReservationInfoBl.getBookingInfo(boardingReservationId);

            responseDto.setData((ReservationInfoDto) booking);
            responseDto.setSuccessful(true);
            responseDto.setMessage("Info");            
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
