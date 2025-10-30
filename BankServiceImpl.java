import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    private final AccountRepo accountRepo = new AccountRepo();
    private final TransactionRepo transactionRepo = new TransactionRepo();

    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerId = UUID.randomUUID().toString();

        String accountNumber = getAccountNumber();
        // String accountNumber = UUID.randomUUID().toString();
        Account account = new Account(accountNumber, customerId, 0.0, accountType);
        accountRepo.saveAccount(account);

        return accountNumber;
    }

    @Override
    public List<Account> listAccounts() {
        // FIX: removed extra semicolons and unnecessary cast
        return accountRepo.findAll().stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    // After refactoring to method
    private String getAccountNumber() {
        int size = accountRepo.findAll().size() + 1;
        return String.format("ACC%05d", size);
    }

    @Override
    public void deposit(String accNumber, Double amount, String note) {
        Account account = accountRepo.findByNumber(accNumber)
                .orElseThrow(() -> new RuntimeException("Account not found " + accNumber));

        // Update balance
        account.setBalance(account.getBalance() + amount);
        accountRepo.updateAccount(account); // <-- make sure this exists

        // ✅ Correct argument order
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(), // id
                Type.DEPOSIT, // type
                account.getAccountNumber(), // account number
                amount, // amount
                LocalDateTime.now(), // timestamp
                note // note
        );

        transactionRepo.add(transaction);
    }

}
