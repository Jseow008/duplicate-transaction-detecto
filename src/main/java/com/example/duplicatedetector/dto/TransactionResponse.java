package com.example.duplicatedetector.dto;

import com.example.duplicatedetector.model.TransactionStatus;
import java.time.Instant;

public class TransactionResponse {
    private String transactionId;
    private TransactionStatus status;
    private String message;
    private Instant processedAt;
    private int windowSize;

    public TransactionResponse(String transactionId, TransactionStatus status, String message, int windowSize) {
        this.transactionId = transactionId;
        this.status = status;
        this.message = message;
        this.processedAt = Instant.now();
        this.windowSize = windowSize;
    }

    // Getters
    public String getTransactionId() { return transactionId; }
    public TransactionStatus getStatus() { return status; }
    public String getMessage() { return message; }
    public Instant getProcessedAt() { return processedAt; }
    public int getWindowSize() { return windowSize; }
} 