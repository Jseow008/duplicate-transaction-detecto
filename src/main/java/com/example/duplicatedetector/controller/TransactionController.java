package com.example.duplicatedetector.controller;

import com.example.duplicatedetector.dto.TransactionRequest;
import com.example.duplicatedetector.dto.TransactionResponse;
import com.example.duplicatedetector.model.Transaction;
import com.example.duplicatedetector.model.TransactionStatus;
import com.example.duplicatedetector.service.DuplicateTransactionDetector;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    
    private final DuplicateTransactionDetector detector;

    @Autowired
    public TransactionController(DuplicateTransactionDetector detector) {
        this.detector = detector;
    }

    @PostMapping("/check")
    public ResponseEntity<TransactionResponse> checkTransaction(@Valid @RequestBody TransactionRequest request) {
        logger.info("Received transaction request: {}", request);
        
        Transaction transaction = new Transaction(
            request.getId(),
            request.getAccountId(),
            request.getAmount(),
            request.getTimestamp()
        );

        boolean isDuplicate = detector.isDuplicate(transaction);
        TransactionStatus status = isDuplicate ? TransactionStatus.DUPLICATE_DETECTED : TransactionStatus.NOT_DUPLICATE;
        String message = isDuplicate ? "Transaction is a duplicate" : "Transaction is not a duplicate";
        
        TransactionResponse response = new TransactionResponse(
            transaction.getId(),
            status,
            message,
            detector.getWindowSize()
        );
        
        logger.info("Transaction processed: id={}, status={}, windowSize={}", 
            transaction.getId(), status, detector.getWindowSize());
        
        return ResponseEntity.ok(response);
    }
} 