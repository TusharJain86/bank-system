import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                case "0" -> isRunning = false;
            }
        }
    }
}