public class Account {
    private String accountNumber;
    private String customerId;
    private double balance;
    private String accountType;

    public Account(String accountNumber, String customerId, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = balance;
        this.accountType = accountType;
    }
}
