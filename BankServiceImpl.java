import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    private final AccountRepo accountRepo = new AccountRepo();
    private final TransactionRepo transactionRepo = new TransactionRepo();
    private final CustomerRepo customerRepo = new CustomerRepo();

    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerId = UUID.randomUUID().toString();
        Customer customer = new Customer(customerId, name, email);
        customerRepo.save(customer);

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

        if (account.getBalance() < amount)
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
    public void transfer(String fromAccount, String toAccount, Double amount, String note) {
        if (fromAccount.equals(toAccount))
            throw new RuntimeException("Cannot transfer to the same account");

        Account fromAcc = accountRepo.findByNumber(fromAccount)
                .orElseThrow(() -> new RuntimeException("Account not found " + fromAccount));

        Account toAcc = accountRepo.findByNumber(toAccount)
                .orElseThrow(() -> new RuntimeException("Account not found " + toAccount));

        if (fromAcc.getBalance() < amount)
            throw new RuntimeException("Insufficient balance in account " + fromAccount);

        fromAcc.setBalance(fromAcc.getBalance() - amount);
        toAcc.setBalance(toAcc.getBalance() + amount);

        accountRepo.updateAccount(fromAcc);
        accountRepo.updateAccount(toAcc);

        transactionRepo.add(new Transaction(
                UUID.randomUUID().toString(),
                Type.TRANSFER_OUT,
                fromAcc.getAccountNumber(),
                amount,
                LocalDateTime.now(),
                note
        ));

        transactionRepo.add(new Transaction(
                UUID.randomUUID().toString(),
                Type.TRANSFER_IN,
                toAcc.getAccountNumber(),
                amount,
                LocalDateTime.now(),
                note
        ));
    }

    @Override
    public List<Transaction> getAccountStatement(String accountNumber) {
        return transactionRepo.findByNumber(accountNumber).stream()
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccountByHolderName(String q) {
        String query = (q == null) ? "" : q.trim().toLowerCase();
        List<Account> result = new ArrayList<>();

        for (Customer c : customerRepo.findAll()) {
            if (c.getName().toLowerCase().contains(query)) {
                Account acc = accountRepo.findByCustomerId(c.getCustomerId());
                if (acc != null) result.add(acc);
            }
        }
        return result;
    }

    private String getAccountNumber() {
        int size = accountRepo.findAll().size() + 1;
        return String.format("ACC%05d", size);
    }
}
