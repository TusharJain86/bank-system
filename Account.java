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

    public Double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Object getTimestamp() {
        throw new UnsupportedOperationException("Unimplemented method 'getTimestamp'");
    }
}
