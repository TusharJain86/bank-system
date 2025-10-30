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
        Account account = new Account(accountNumber, customerId, 0.0, accountType);
        accountRepo.saveAccount(account);

        return accountNumber;
    }

    @Override
    public List<Account> listAccounts() {
        return accountRepo.findAll().stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    private String getAccountNumber() {
        int size = accountRepo.findAll().size() + 1;
        return String.format("ACC%05d", size);
    }

    @Override
    public void deposit(String accNumber, Double amount, String note) {
        Account account = accountRepo.findByNumber(accNumber)
            .orElseThrow(() -> new RuntimeException("Account not found " + accNumber));

        account.setBalance(account.getBalance() + amount);
        accountRepo.updateAccount(account);

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                Type.DEPOSIT,
                account.getAccountNumber(),
                amount,
                LocalDateTime.now(),
                note
        );
        transactionRepo.add(transaction);
    }

    @Override
    public void withdraw(String accNumber, double amount, String note) {
        Account account = accountRepo.findByNumber(accNumber)
                .orElseThrow(() -> new RuntimeException("Account not found " + accNumber));

        // ✅ Using compareTo (matches the video)
        if (account.getBalance().compareTo(amount) < 0)
            throw new RuntimeException("Insufficient balance in account " + accNumber);

        account.setBalance(account.getBalance() - amount);
        accountRepo.updateAccount(account);

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                Type.WITHDRAWAL,
                account.getAccountNumber(),
                amount,
                LocalDateTime.now(),
                note
        );
        transactionRepo.add(transaction);
    }

    @Override
    public void transfer(String fromAccount, String to, Double amount, String string) {
    }
}
