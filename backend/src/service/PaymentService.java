package service;

import factory.PaymentFactory;
import payment.PaymentMethod;

public class PaymentService {

    public String processPayment(double amount, String mode) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        PaymentMethod paymentMethod = PaymentFactory.getPaymentMethod(mode);
        return paymentMethod.pay(amount);
    }
}
