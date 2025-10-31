import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankServiceImpl();
        System.out.println("Hello, Welcome to our bank!");

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""
                        1. Create Account
                        2. Deposit Money
                        3. Withdraw Money
                        4. Transfer Money
                        5. Account Statement
                        6. List All Accounts
                        7. Search Account by Holder's Name
                        0. Exit
                    """);
            System.out.print("Please select an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> openAccount(scanner, bankService);
                case "2" -> deposit(scanner, bankService);
                case "3" -> withdraw(scanner, bankService);
                case "4" -> transfer(scanner, bankService);
                case "5" -> accountStatement(scanner, bankService);
                case "6" -> listAllAccounts(bankService);
                case "7" -> searchAccountByHolderName(scanner, bankService);
                case "0" -> isRunning = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("Thank you for banking with us!");
    }

    private static void openAccount(Scanner scanner, BankService bankService) {
        System.out.print("Customer Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Customer Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Account Type (Savings/Current): ");
        String type = scanner.nextLine().trim();

        System.out.print("Initial Deposit Amount: ");
        Double initial = Double.parseDouble(scanner.nextLine().trim());

        String accountNumber = bankService.openAccount(name, email, type);

        if (initial > 0) {
            bankService.deposit(accountNumber, initial, "Initial deposit");
        }

        System.out.println("✅ Account created successfully! Your account number is: " + accountNumber);
    }

    private static void deposit(Scanner scanner, BankService bankService) {
        System.out.print("Account Number: ");
        String accNumber = scanner.nextLine().trim();

        System.out.print("Amount: ");
        Double amount = Double.parseDouble(scanner.nextLine().trim());

        bankService.deposit(accNumber, amount, "Deposit via Main");
        System.out.println("✅ Deposit successful!");
    }

    private static void withdraw(Scanner scanner, BankService bankService) {
        System.out.print("Account Number: ");
        String accNumber = scanner.nextLine().trim();

        System.out.print("Amount: ");
        Double amount = Double.parseDouble(scanner.nextLine().trim());

        bankService.withdraw(accNumber, amount, "Withdraw via Main");
        System.out.println("✅ Withdraw successful!");
    }

    private static void transfer(Scanner scanner, BankService bankService) {
        System.out.print("From Account: ");
        String fromAccount = scanner.nextLine().trim();

        System.out.print("To Account: ");
        String to = scanner.nextLine().trim();

        System.out.print("Amount: ");
        Double amount = Double.parseDouble(scanner.nextLine().trim());

        bankService.transfer(fromAccount, to, amount, "Transfer");
        System.out.println("✅ Transfer successful!");
    }

    private static void accountStatement(Scanner scanner, BankService bankService) {
        System.out.print("Account Number: ");
        String accStatement = scanner.nextLine().trim();
        bankService.getAccountStatement(accStatement).forEach(t ->
            System.out.println(t.getTimestamp() + " | " + t.getType() + " | " + t.getAmount() + " | " + t.getNote())
        );
    }

    private static void listAllAccounts(BankService bankService) {
        bankService.listAccounts().forEach(a ->
            System.out.println(a.getAccountNumber() + " - " + a.getAccountType() + " - Balance: " + a.getBalance())
        );
    }

    private static void searchAccountByHolderName(Scanner scanner, BankService bankService) {
        System.out.print("Customer name contains: ");
        String q = scanner.nextLine().trim();
        bankService.searchAccountByHolderName(q).forEach(a ->
            System.out.println(a.getAccountNumber() + " | " + a.getAccountType() + " | Balance: " + a.getBalance())
        );
    }
}
