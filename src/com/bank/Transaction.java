package com.bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type;
    private double amount;
    private double postBalance;
    private String timestamp;

    public Transaction(String type, double amount, double postBalance) {
        this.type = type;
        this.amount = amount;
        this.postBalance = postBalance;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[%s] %-10s | Amount: $%.2f | Balance: $%.2f", 
                timestamp, type, amount, postBalance);
    }
}