import java.util.List;

public interface BankService {
    String openAccount(String name, String email, String accountType);
    List<Account> listAccounts();
    void deposit(String accNumber, Double amount, String note);
    void withdraw(String accNumber, double amount, String note);
    void transfer(String fromAccount, String toAccount, Double amount, String note);
    List<Transaction> getAccountStatement(String accStatement);
    List<Account> searchAccountByHolderName(String q);
}
