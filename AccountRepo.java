import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountRepo {
    private final Map<String, Account> accountsByNumber = new HashMap<>();

    // Save new account
    public void saveAccount(Account account) {
        accountsByNumber.put(account.getAccountNumber(), account);
    }

    // Get all accounts as a list
    public List<Account> findAll() {
        return new ArrayList<>(accountsByNumber.values());
    }

    // Find account by account number
    public Optional<Account> findByNumber(String accNumber) {
        return Optional.ofNullable(accountsByNumber.get(accNumber));
    }

    // ✅ Update existing account (used by deposit/withdraw)
    public void updateAccount(Account account) {
        if (accountsByNumber.containsKey(account.getAccountNumber())) {
            accountsByNumber.put(account.getAccountNumber(), account);
        }
    }
}
