import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class User {
    String username;
    String password;
    double balance;
    List<String> transactionHistory;

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }
}

 class ATM {
    static List<User> users = new ArrayList<>();
    static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the ATM");
            System.out.println("1. Create an account");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                createAccount(scanner);
            } else if (choice == 2) {
                login(scanner);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void createAccount(Scanner scanner) {
        System.out.println("Enter a username:");
        String username = scanner.next();
        System.out.println("Enter a password:");
        String password = scanner.next();

        User newUser = new User(username, password);
        users.add(newUser);

        System.out.println("Account created successfully.");
        System.out.println("Account Details");
        System.out.println("username:"+ username);

    }

    public static void login(Scanner scanner) {
        System.out.println("Enter your username:");
        String username = scanner.next();
        System.out.println("Enter your password:");
        String password = scanner.next();

        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                currentUser = user;
                System.out.println("Login successful.");
                showLoggedInMenu(scanner);
                return;
            }
        }

        System.out.println("Login failed. Invalid username or password.");
    }

    public static void showLoggedInMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Quit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                deposit(scanner);
            } else if (choice == 2) {
                withdraw(scanner);
            } else if (choice == 3) {
                transfer(scanner);
            } else if (choice == 4) {
                displayTransactionHistory();
            } else if (choice == 5) {
                currentUser = null;
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void deposit(Scanner scanner) {
        System.out.println("Enter the amount to deposit:");
        double amount = scanner.nextDouble();
        currentUser.balance += amount;
        currentUser.transactionHistory.add("Deposited $" + amount);
        System.out.println("Deposit successful.");
    }

    public static void withdraw(Scanner scanner) {
        System.out.println("Enter the amount to withdraw:");
        double amount = scanner.nextDouble();
        if (amount <= currentUser.balance) {
            currentUser.balance -= amount;
            currentUser.transactionHistory.add("Withdrawn $" + amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public static void transfer(Scanner scanner) {
        System.out.println("Enter the recipient's username:");
        String recipientUsername = scanner.next();
        System.out.println("Enter the amount to transfer:");
        double amount = scanner.nextDouble();

        for (User user : users) {
            if (user.username.equals(recipientUsername)) {
                if (amount <= currentUser.balance) {
                    currentUser.balance -= amount;
                    user.balance += amount;
                    currentUser.transactionHistory.add("Transferred $" + amount + " to " + user.username);
                    user.transactionHistory.add("Received $" + amount + " from " + currentUser.username);
                    System.out.println("Transfer successful.");
                } else {
                    System.out.println("Insufficient balance.");
                }
                return;
            }
        }

        System.out.println("Recipient not found.");
    }

    public static void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : currentUser.transactionHistory) {
            System.out.println(transaction);
        }
    }
}
