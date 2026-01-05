package payment;

public class CardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Amount "+amount+" paid using Card.");
    }
}