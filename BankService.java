import java.util.List;

public interface BankService {
    String openAccount(String name, String email, String accountType);
    List<Account> listAccounts();
    void deposit(String accNumber, Double amount, String note);
    void withdraw(String accNumber, double amount, String withdrawal);
    void transfer(String fromAccount, String to, Double amount, String string);
}
