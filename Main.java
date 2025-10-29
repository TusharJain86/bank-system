import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankServiceImpl();
        System.out.println("Hello, Welcome to our bank!");

        Boolean isRunning = true;
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
            System.out.println("You selected option: " + choice);

            switch (choice){
                case "1" -> openAccount(scanner, bankService);
                case "2" -> deposit(scanner);
                case "3" -> withdraw(scanner);
                case "4" -> transfer(scanner);
                case "5" -> accountStatement(scanner);
                case "6" -> listAllAccounts();
                case "7" -> searchAccountByHolderName(scanner);
                case "0" -> isRunning = false;
            }
        }
    }

    private static void openAccount(Scanner scanner, BankService bankService) {

        System.out.println("Customer Name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Customer Email: ");
        String email = scanner.nextLine().trim();

        System.out.println("Account Type (Savings/Current): ");
        String type = scanner.nextLine().trim();

        System.out.println("Initial Deposit Amount: ");
        String amountStr = scanner.nextLine().trim();
        double initial = Double.valueOf(amountStr);
        bankService.openAccount(name, email, type);
    }

    private static void deposit(Scanner scanner) {}

    private static void withdraw(Scanner scanner) {}

    private static void transfer(Scanner scanner) {}

    private static void accountStatement(Scanner scanner) {}

    private static void listAllAccounts() {}

    private static void searchAccountByHolderName(Scanner scanner) {}
}