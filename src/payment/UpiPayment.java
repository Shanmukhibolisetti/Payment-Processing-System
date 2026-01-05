package payment;

public class UpiPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Amount "+amount+" paid using UPI.");
    }
}