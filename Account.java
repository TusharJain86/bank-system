public class Account {
    private String accountNumber;
    private String customerId;
    private Double balance;
    private String accountType;

    public Account(String accountNumber, String customerId, Double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // ✅ FIXED: return a double, not String
    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    // (Optional) Add setters if you plan to update these fields later
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
