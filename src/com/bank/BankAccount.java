package com.bank;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private String pin;
    private double balance;
    private List<Transaction> transactionHistory;

    public BankAccount(String accountNumber, String accountHolderName, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        
        if (initialBalance > 0) {
            transactionHistory.add(new Transaction("INITIAL DEP", initialBalance, initialBalance));
        }
    }

    // --- Getters & Setters ---
    public String getAccountNumber() { 
        return accountNumber; 
    }

    public String getAccountHolderName() { 
        return accountHolderName; 
    }

    public double getBalance() { 
        return balance; 
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        logAction("NAME UPDATE", 0.0);
    }

    public void setPin(String newPin) {
        this.pin = newPin;
        logAction("PIN UPDATE", 0.0);
    }

    // --- PIN Validation Method ---
    public boolean validatePin(String inputPin) {
        return this.pin != null && this.pin.equals(inputPin);
    }

    // --- Banking Operations ---
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("DEPOSIT", amount, balance));
            System.out.printf("Successfully deposited $%.2f. New Balance: $%.2f%n", amount, balance);
        } else {
            System.out.println("Error: Deposit amount must be greater than zero.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
        } else if (amount > balance) {
            System.out.println("Error: Insufficient funds.");
        } else {
            balance -= amount;
            transactionHistory.add(new Transaction("WITHDRAWAL", amount, balance));
            System.out.printf("Successfully withdrew $%.2f. Remaining Balance: $%.2f%n", amount, balance);
        }
    }

    // --- Logging & History Methods ---
    public void logAction(String actionType, double amount) {
        transactionHistory.add(new Transaction(actionType, amount, balance));
    }

    public void viewTransactionHistory() {
        System.out.println("\n--- Transaction History for " + accountNumber + " ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No activity found.");
        } else {
            for (Transaction t : transactionHistory) {
                System.out.println(t);
            }
        }
    }
}