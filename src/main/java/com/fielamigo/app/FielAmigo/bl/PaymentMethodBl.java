package com.fielamigo.app.FielAmigo.bl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dao.FaPaymentMethodDao;
import com.fielamigo.app.FielAmigo.dto.PaymentMethodReqDto;
import com.fielamigo.app.FielAmigo.dto.PaymentMethodResDto;
import com.fielamigo.app.FielAmigo.entity.FaPaymentMethod;

@Service
public class PaymentMethodBl {

    private FaPaymentMethodDao faPaymentMethodDao;

    public PaymentMethodBl(FaPaymentMethodDao faPaymentMethodDao) {
        this.faPaymentMethodDao = faPaymentMethodDao;
    }

    /**
     * Method to get all the payment methods of a user by userId.
     * @param userId the user id.
     * @return the list of payment methods.
     */
    public List<PaymentMethodResDto> getPaymentMethods(int userId) {
        return faPaymentMethodDao.getPaymentMethods(userId);
    }

    /**
     * Method to create a new payment method
     * @param userId the user id
     * @param newPaymentMethod the new payment method
     */
    public void addPaymentMethod(Integer userId, PaymentMethodReqDto newPaymentMethod) {
        FaPaymentMethod paymentMethod = new FaPaymentMethod();
        paymentMethod.setUserId(userId);
        paymentMethod.setName(newPaymentMethod.getName());
        Integer lastDigits = Integer.parseInt(newPaymentMethod.getNumbers().toString().substring(12));
        paymentMethod.setLastDigits(lastDigits);
        paymentMethod.setExpirationDate(newPaymentMethod.getExpirationDate());
        paymentMethod.setNumbers(newPaymentMethod.getNumbers());

        System.out.println(paymentMethod);

        faPaymentMethodDao.addPaymentMethod(paymentMethod);
    }

}
