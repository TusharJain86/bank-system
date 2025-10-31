import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepo {
    private final Map<String, Customer> customersID = new HashMap<>();

    public void save(Customer customer) {
        customersID.put(customer.getCustomerId(), customer);
    }

    public Customer findById(String id) {
        return customersID.get(id);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customersID.values());
    }
}
