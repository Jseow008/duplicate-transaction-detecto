# Duplicate Transaction Detector

A Spring Boot application that detects duplicate transactions within a specified time window.

## Features
- REST API for transaction validation
- Web interface for easy testing
- Configurable time window for duplicate detection
- Real-time transaction status updates
- Detailed error handling and validation

## Setup
1. Ensure you have Java 17+ and Maven installed
2. Clone the repository
3. Run `mvn clean package`
4. Execute `java -jar target/duplicate-detector-0.0.1-SNAPSHOT.jar`
5. Access the web interface at `http://localhost:8080`

## API Endpoints
- POST `/api/transactions/check` - Check if a transaction is a duplicate
  - Request body: `{"id": "string", "accountId": "string", "amount": number, "timestamp": number}`
  - Response: Transaction status with details

## Technologies Used
- Spring Boot
- Maven
- Bootstrap
- JavaScript 