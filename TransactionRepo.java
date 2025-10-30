import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepo {
    private final Map<String, List<Transaction>> transactionsByAccount = new HashMap<>();

    public void add(Transaction transaction) {
        List<Transaction> list = transactionsByAccount.computeIfAbsent(transaction.getAccountNumber(), k -> new ArrayList<>());
        list.add(transaction);
    }
}
