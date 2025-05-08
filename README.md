# Duplicate Transaction Detector

A modern Spring Boot web application to detect duplicate transactions within a configurable time window. Features a rich, interactive UI for testing and visualization.

## Features
- **REST API** for transaction validation
- **Modern Web Interface** with:
  - Date-time picker for user-friendly input
  - Random transaction generator (with high chance of duplicates)
  - Real-time transaction history table
  - Visual timeline of transactions and duplicate window
  - Statistics panel (total, duplicates, success rate, avg. processing time)
  - Filtering by status and account
  - Export history to CSV
  - Clear history button
  - Transaction details modal (click any row or timeline dot)
- **Validation and error handling** with clear feedback
- **Responsive design** (works on desktop and mobile)

## Setup
1. Ensure you have **Java 17+** and **Maven** installed
2. Clone the repository
3. Run `mvn clean package`
4. Start the app: `java -jar target/duplicate-detector-0.0.1-SNAPSHOT.jar`
5. Open your browser at [http://localhost:8080](http://localhost:8080)

## How to Use
- **Add a transaction:** Fill the form and click "Check Transaction"
- **Generate random:** Click "Generate Random Transaction" for quick testing
- **View timeline:** See colored dots for each transaction (green: not duplicate, red: duplicate, yellow: error)
- **Filter:** Use dropdown and account filter to focus on specific transactions
- **Export:** Download visible history as CSV
- **Clear:** Remove all transactions and reset stats
- **Details:** Click any row or timeline dot for full transaction info

## API Endpoints
- `POST /api/transactions/check` — Check if a transaction is a duplicate
  - Request: `{ "id": "string", "accountId": "string", "amount": number, "timestamp": number }`
  - Response: Transaction status, message, and details

## Technologies Used
- Spring Boot
- Maven
- Bootstrap 5
- JavaScript (ES6+)
- [flatpickr](https://flatpickr.js.org/) for date-time picker

---

**Enjoy testing and visualizing duplicate detection!** 