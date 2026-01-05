package factory;

import payment.CardPayment;
import payment.NetBankingPayment;
import payment.PaymentMethod;
import payment.UpiPayment;

public class PaymentFactory {

    public static PaymentMethod getPaymentMethod(String mode) {

        if (mode == null) {
            throw new IllegalArgumentException("Payment mode cannot be null");
        }

        switch (mode.toUpperCase()) {
            case "UPI":
                return new UpiPayment();
            case "CARD":
                return new CardPayment();
            case "NET_BANKING":
                return new NetBankingPayment();
            default:
                throw new IllegalArgumentException("Invalid payment mode: " + mode);
        }
    }
}
