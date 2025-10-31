import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private Type type;
    private String accountNumber;
    private double amount;
    private LocalDateTime timestamp;
    private String note;

    public Transaction(String id, Type type, String accountNumber, double amount, LocalDateTime timestamp, String note) {
        this.id = id;
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.timestamp = timestamp;
        this.note = note;
    }

    public String getId() { return id; }
    public Type getType() { return type; }
    public String getAccountNumber() { return accountNumber; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getNote() { return note; }
}
