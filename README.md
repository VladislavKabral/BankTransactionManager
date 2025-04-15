# Bank transaction manager

**Brief**  
This project is designed to manage transactions and expense limits.

---

## 📂 Content

1. [Description](#-description)
2. [Installation](#-installation)
3. [Exploitation](#-exploitation)
4. [Technologies](#-technologies)
5. [Функциональность](#-functionality)

---

## 📝 Description

This project provides a REST API for working with transactions and managing expense limits.

---

## 🚀 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/VladislavKabral/BankTransactionManager.git

2. Move to the folder with the project:

   ```bash
   cd 'Your folder with the project'

3. Install the dependencies:

   ```bash
   mvn install
   
4. Also, you need to change environment variable for yourself. Before starting, change DB_HOST, DB_PORT, DB_NAME, DB_USERNAME, DB_PASSWORD and EXTERNAL_API_KEY.

5. Now, you can use the app by starting .jar file, which you can find in the 'target' folder. 

## 💻 Exploitation

1. You can start working with the app with .jar file.

   ```bash
   java -jar BankTransactionManager-0.0.1-SNAPSHOT.jar   
   
2. Use an application to send requests, for example, Postman.
   
   * GET: http://localhost:8081/transactions
   * GET: http://localhost:8081/transactions/limited
   * POST: http://localhost:8081/transactions
   * POST: http://localhost:8081/limits

   P.S. You can find DTO schemes for POST requests in the project  structure.

---

## 🛠️ Technologies

   * Java 17
   * Spring Boot 3.4.4
   * Spring Data JPA
   * Maven
   * PostgreSQL
   * Liquibase
   * Lombok
   * Redis
   * Docker

---

## ✨ Functionality

   * Getting information about completed transactions
   * Storing the monthly expense limit in US dollars (USD) separately for two categories of expenses: products and services
   * Downloading the latest currency rates
   * Setting a new expense limit
   * Calculating transactions that exceed the monthly limit

