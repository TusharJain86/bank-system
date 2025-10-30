import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepo {
    private final Map<String, Account> accountsByNumber = new HashMap<>();
    public Object findAll;

    public void saveAccount(Account account) {
        accountsByNumber.put(account.getAccountNumber(), account);
    }

    public List<Account> findAll() {
        return new ArrayList<>(accountsByNumber.values());
    }
}