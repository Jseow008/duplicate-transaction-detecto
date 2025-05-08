package com.example.duplicatedetector.service;

import com.example.duplicatedetector.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DuplicateTransactionDetector {
    private static final Logger logger = LoggerFactory.getLogger(DuplicateTransactionDetector.class);
    public static final long TIME_WINDOW = 30; // in seconds

    private final Map<String, Deque<Long>> recentTransactions = new HashMap<>();

    public boolean isDuplicate(Transaction txn) {
        String key = txn.getAccountId() + "_" + txn.getAmount();
        logger.debug("Checking transaction: id={}, key={}", txn.getId(), key);

        // Get the list of timestamps for this key, or create a new one if it doesn't exist
        Deque<Long> timestamps = recentTransactions.getOrDefault(key, new LinkedList<>());

        // 1. Prune timestamps that are older than TIME_WINDOW
        while (!timestamps.isEmpty() && timestamps.peekFirst() < txn.getTimestamp() - TIME_WINDOW) {
            timestamps.pollFirst();
        }

        // 2. Check if the transaction is a duplicate in remaining window
        if (!timestamps.isEmpty()) {
            if (Math.abs(txn.getTimestamp() - timestamps.peekFirst()) <= TIME_WINDOW) {
                logger.debug("Duplicate detected: id={}, key={}", txn.getId(), key);
                return true;
            }
        }     

        // 3. Not duplicated, Add the transaction to the list of recent transactions
        timestamps.addLast(txn.getTimestamp());
        recentTransactions.put(key, timestamps);
        logger.debug("Transaction added: id={}, key={}, windowSize={}", txn.getId(), key, timestamps.size());

        return false;
    }

    public int getWindowSize() {
        return recentTransactions.size();
    }
} 