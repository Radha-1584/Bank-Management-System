package com.bank;

import java.util.Scanner;

public class Main {
    private static BankAccount currentAccount = null;
    private static final SessionManager session = new SessionManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount demoAccount = new BankAccount("ACC1001", "Alex Morgan", "1234", 500.00);

        while (true) {
            if (currentAccount == null) {
                showPreLoginMenu(scanner, demoAccount);
            } else {
                showLoggedInMenu(scanner);
            }
        }
    }

    private static void showPreLoginMenu(Scanner scanner, BankAccount demoAccount) {
        System.out.println("\n========================================");
        System.out.println("    WELCOME TO THE BANKING SYSTEM       ");
        System.out.println("========================================");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                currentAccount = createAccount(scanner);
                if (currentAccount != null) {
                    session.resetTimer();
                    System.out.println("\nAccount created and logged in!");
                }
                break;
            case "2":
                System.out.print("Enter Account Number (Demo: ACC1001): ");
                String accNum = scanner.nextLine();
                System.out.print("Enter PIN (Demo: 1234): ");
                String pin = scanner.nextLine();

                if (demoAccount != null && demoAccount.getAccountNumber().equalsIgnoreCase(accNum) && demoAccount.validatePin(pin)) {
                    currentAccount = demoAccount;
                    session.resetTimer();
                    System.out.println("\nLogin successful!");
                } else if (currentAccount != null && currentAccount.getAccountNumber().equalsIgnoreCase(accNum) && currentAccount.validatePin(pin)) {
                    session.resetTimer();
                    System.out.println("\nLogin successful!");
                } else {
                    System.out.println("\nInvalid Account Number or PIN.");
                }
                break;
            case "3":
                System.out.println("Thank you for using our system!");
                System.exit(0);
            default:
                System.out.println("Invalid selection.");
        }
    }

    private static void showLoggedInMenu(Scanner scanner) {
        if (session.isTimedOut()) {
            handleTimeout();
            return;
        }

        System.out.println("\n========================================");
        System.out.println(" MAIN MENU | Acc: " + currentAccount.getAccountNumber() + " | User: " + currentAccount.getAccountHolderName());
        System.out.println("========================================");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. View Transaction History");
        System.out.println("5. Update Name/PIN");
        System.out.println("6. Delete Account");
        System.out.println("7. Logout");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();

        if (session.isTimedOut()) {
            handleTimeout();
            return;
        }

        session.resetTimer();

        switch (choice) {
            case "1":
                currentAccount.logAction("BALANCE CHK", 0.0);
                System.out.printf("%nCurrent Balance: $%.2f%n", currentAccount.getBalance());
                break;
            case "2":
                System.out.print("Enter deposit amount: $");
                try {
                    currentAccount.deposit(Double.parseDouble(scanner.nextLine()));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount format.");
                }
                break;
            case "3":
                System.out.print("Enter withdrawal amount: $");
                try {
                    currentAccount.withdraw(Double.parseDouble(scanner.nextLine()));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount format.");
                }
                break;
            case "4":
                currentAccount.viewTransactionHistory();
                break;
            case "5":
                updateDetails(scanner);
                break;
            case "6":
                deleteAccount(scanner);
                break;
            case "7":
                currentAccount.logAction("LOGOUT", 0.0);
                System.out.println("Logged out successfully.");
                currentAccount = null;
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static BankAccount createAccount(Scanner scanner) {
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Set 4-digit PIN: ");
        String pin = scanner.nextLine();
        System.out.print("Initial Deposit: $");
        try {
            double deposit = Double.parseDouble(scanner.nextLine());
            String accNum = "ACC" + (1000 + (int)(Math.random() * 9000));
            System.out.println("Account Created Successfully! Your Account Number is: " + accNum);
            return new BankAccount(accNum, name, pin, deposit);
        } catch (NumberFormatException e) {
            System.out.println("Invalid initial deposit.");
            return null;
        }
    }

    private static void updateDetails(Scanner scanner) {
        System.out.println("\n1. Update Name\n2. Update PIN");
        System.out.print("Choose option: ");
        String option = scanner.nextLine();

        if ("1".equals(option)) {
            System.out.print("Enter new Name: ");
            currentAccount.setAccountHolderName(scanner.nextLine());
            System.out.println("Name updated!");
        } else if ("2".equals(option)) {
            System.out.print("Enter current PIN: ");
            if (currentAccount.validatePin(scanner.nextLine())) {
                System.out.print("Enter new PIN: ");
                currentAccount.setPin(scanner.nextLine());
                System.out.println("PIN updated!");
            } else {
                System.out.println("Incorrect current PIN.");
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void deleteAccount(Scanner scanner) {
        System.out.println("\n⚠️ WARNING: You are about to delete your account!");
        System.out.print("Enter your PIN to confirm deletion: ");
        String pinInput = scanner.nextLine();

        if (currentAccount.validatePin(pinInput)) {
            System.out.print("Are you sure you want to permanently delete this account? (Y/N): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("Y")) {
                System.out.println("\nAccount " + currentAccount.getAccountNumber() + " deleted successfully.");
                currentAccount = null;
            } else {
                System.out.println("Account deletion cancelled.");
            }
        } else {
            System.out.println("Error: Incorrect PIN. Account deletion failed.");
        }
    }

    private static void handleTimeout() {
        if (currentAccount != null) {
            currentAccount.logAction("TIMEOUT", 0.0);
        }
        System.out.println("\n🕒 SESSION EXPIRED: Logged out due to 2 minutes of inactivity.");
        currentAccount = null;
    }
}