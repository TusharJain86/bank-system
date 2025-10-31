import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountRepo {
    private final Map<String, Account> accountsByNumber = new HashMap<>();

    public void saveAccount(Account account) {
        accountsByNumber.put(account.getAccountNumber(), account);
    }

    public List<Account> findAll() {
        return new ArrayList<>(accountsByNumber.values());
    }

    public Optional<Account> findByNumber(String accNumber) {
        return Optional.ofNullable(accountsByNumber.get(accNumber));
    }

    public void updateAccount(Account account) {
        if (accountsByNumber.containsKey(account.getAccountNumber())) {
            accountsByNumber.put(account.getAccountNumber(), account);
        }
    }

    public Account findByCustomerId(Object id) {
        for (Account a : accountsByNumber.values()) {
            if (a.getCustomerId().equals(id)) {
                return a;
            }
        }
        return null;
    }
}
