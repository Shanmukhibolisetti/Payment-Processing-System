package payment;

public class UpiPayment implements PaymentMethod {
    @Override
    public String pay(double amount) {
        return ("Amount "+amount+" paid using UPI.");
    }
}