package payment;

public class NetBankingPayment implements PaymentMethod {
    @Override
    public String pay(double amount) {
        return ("Amount "+amount+" paid using Net Banking.");
    }
}