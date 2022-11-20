package com.fielamigo.app.FielAmigo.service;

import com.fielamigo.app.FielAmigo.dto.BoardingReservationReqDto;

public interface PaymentService {
    public String processBoardingPayment(Integer userId, BoardingReservationReqDto req);
}
