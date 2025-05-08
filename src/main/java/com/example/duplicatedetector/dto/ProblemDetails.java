package com.example.duplicatedetector.dto;

import java.time.Instant;

public class ProblemDetails {
    private String type;
    private String title;
    private int status;
    private String detail;
    private Instant timestamp;
    private String instance;

    public ProblemDetails(String type, String title, int status, String detail, String instance) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timestamp = Instant.now();
        this.instance = instance;
    }

    // Getters
    public String getType() { return type; }
    public String getTitle() { return title; }
    public int getStatus() { return status; }
    public String getDetail() { return detail; }
    public Instant getTimestamp() { return timestamp; }
    public String getInstance() { return instance; }
} 