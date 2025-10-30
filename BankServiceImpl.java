import java.util.UUID;

public class BankServiceImpl implements BankService {

    private final AccountRepo accountRepo = new AccountRepo();

    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerId = UUID.randomUUID().toString();

        int temp = accountRepo.findAll().size()+1;
        String accountNumber = String.format("Ac%06d", temp);
        //String accountNumber = UUID.randomUUID().toString();
        Account account = new Account(accountNumber, customerId, 0.0, accountType);
        accountRepo.saveAccount(account);

        return accountNumber;
    }
}