package payment;

public class NetBankingPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Amount "+amount+" paid using Net Banking.");
    }
}