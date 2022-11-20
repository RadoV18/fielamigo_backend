package com.fielamigo.app.FielAmigo.service;

import java.util.UUID;

import com.fielamigo.app.FielAmigo.dto.BoardingReservationReqDto;

public class MockPaymentServiceImpl implements PaymentService {

    @Override
    public String processBoardingPayment(Integer userId, BoardingReservationReqDto req) {
        return UUID.randomUUID().toString();
    }

}
