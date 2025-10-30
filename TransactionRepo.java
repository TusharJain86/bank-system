import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepo {
    private final Map<String, List<Transaction>> transactionsByAccount = new HashMap<>();

    public void add(Transaction transaction) {
        }
}
