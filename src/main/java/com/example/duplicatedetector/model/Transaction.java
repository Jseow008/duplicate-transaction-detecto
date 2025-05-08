package com.example.duplicatedetector.model;

public class Transaction {
    // immutable fields
    private final String id;
    private final String accountId;
    private final double amount;
    private final long timestamp; 

    public Transaction(String id, String accountId, double amount, long timestamp) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    // Getters
    public String getId() { return id; }
    public String getAccountId() { return accountId; }
    public double getAmount() { return amount; }
    public long getTimestamp() { return timestamp; }
} 