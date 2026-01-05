import payment.CardPayment;
import payment.PaymentMethod;
import payment.UpiPayment;
import service.PaymentService;

public class Main {

    public static void main(String[] args) {

        PaymentMethod upi = new UpiPayment();
        PaymentService service1 = new PaymentService(upi);
        service1.processPayment(500);

        PaymentMethod card = new CardPayment();
        PaymentService service2 = new PaymentService(card);
        service2.processPayment(1200);
    }
}
