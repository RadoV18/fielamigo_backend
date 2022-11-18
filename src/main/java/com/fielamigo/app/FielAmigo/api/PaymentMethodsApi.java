package com.fielamigo.app.FielAmigo.api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fielamigo.app.FielAmigo.bl.PaymentMethodBl;
import com.fielamigo.app.FielAmigo.dto.PaymentMethodReqDto;
import com.fielamigo.app.FielAmigo.dto.ResponseDto;
import com.fielamigo.app.FielAmigo.utils.AuthUtil;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;
import com.fielamigo.app.FielAmigo.utils.JwtUtil;
import com.fielamigo.app.FielAmigo.utils.UnauthorizedException;

@RestController
@RequestMapping("api/v1/payment-methods")
public class PaymentMethodsApi {

    private PaymentMethodBl paymentMethodBl;

    public PaymentMethodsApi(PaymentMethodBl paymentMethodBl) {
        this.paymentMethodBl = paymentMethodBl;
    }

    /**
     * Endpoint to get all the payment methods of a user by userId.
     */

    /**
     * Endpoint to add a new payment method.
     */
    @PostMapping
    public ResponseEntity<ResponseDto<PaymentMethodReqDto>> addPaymentMethod(
        @RequestHeader Map<String, String> headers, @RequestBody PaymentMethodReqDto paymentMethodReqDto  
    ) {
        ResponseDto<PaymentMethodReqDto> responseDto =
            new ResponseDto<>(null, null, false);
        try {
            // TODO: validate data

            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "ADD_PAYMENT_METHOD");
            // get the user id from the token
            int userId = JwtUtil.getUserIdFromToken(jwt);

            // add the payment method
            paymentMethodBl.addPaymentMethod(userId, paymentMethodReqDto);

            responseDto.setSuccessful(true);
            return new ResponseEntity<ResponseDto<PaymentMethodReqDto>>(responseDto, HttpStatus.CREATED);

        } catch (FielAmigoException e) {
            return new ResponseEntity<ResponseDto<PaymentMethodReqDto>>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<ResponseDto<PaymentMethodReqDto>>(responseDto, HttpStatus.UNAUTHORIZED);
        }
        
    }
}
