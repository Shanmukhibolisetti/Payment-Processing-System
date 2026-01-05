package payment;

public class CardPayment implements PaymentMethod {
    @Override
    public String pay(double amount) {
        return ("Amount "+amount+" paid using Card.");
    }
}