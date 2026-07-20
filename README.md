# Bank Management System (Java)

A console-based **Bank Management System** developed using **Core Java**.
This project demonstrates fundamental Object-Oriented Programming (OOP) concepts and simulates basic banking operations through a menu-driven interface.

---

## 📌 Project Overview

The **Bank Management System** allows users to manage bank accounts efficiently by performing operations such as account creation, deposits, withdrawals, balance inquiries using a PIN, viewing transaction history, updating account details, and safe account deletion. The project focuses on clean logic, input validation, modular design, and dynamic session management.

This application is developed as a learning-oriented project to strengthen Java programming and problem-solving skills.

---

## ⚙️ Functional Features

* **Create a new bank account** with unique auto-generated account numbers
* **Deposit funds** into an existing account
* **Withdraw funds** with sufficient balance checks
* **Secure balance inquiry** using PIN authentication
* **View transaction history** complete with date/timestamps
* **Update account details** (Account Holder Name and Security PIN)
* **Delete account** safely with PIN verification and double confirmation
* **2-minute session timeout** for dynamic user inactivity handling
* **Robust input validation** to prevent invalid entries or system crashes

---

## 🛠️ Technologies & Concepts Used

* **Java (Core Java)**
* **Object-Oriented Programming**
  * Classes & Objects
  * Constructors
  * Encapsulation
* **Collections Framework**
  * `ArrayList` for transaction audit logging
* **Date & Time API**
  * `LocalDateTime` and `DateTimeFormatter` for accurate timestamps
* **Scanner Class**
  * User input handling
* **Control Flow**
  * Loops & Switch statements
* **Exception Handling**
  * `try-catch` blocks for input validation

---

## 📁 Project Structure

```text
BankManagementSystem/
│
├── bin/                             # Compiled Java byte-code (.class files)
│
├── src/                             # Source code folder
│   └── com/
│       └── bank/                    # Project package name
│           ├── Main.java            # Entry point & interactive console menu
│           ├── BankAccount.java     # Data model for account logic & balances
│           ├── Transaction.java     # Model for logging individual transaction history
│           └── SessionManager.java  # Handler for tracking 2-minute inactivity timeout
│
├── .gitignore                       # Git exclusion list
└── README.md                        # Project documentation


▶️ How to Run the Application

Ensure Java JDK is installed on your system.

Clone the repository or download the source code.

Open the project in a Java IDE (VS Code / IntelliJ IDEA / Eclipse).

Compile and run the Main.java file.

Follow the on-screen menu instructions.

🔐 Default Security Details
Default Demo Account Number: ACC1001

Default PIN for Demo Account: 1234
(Note: Users can also create new accounts with custom names, custom PINs, and update their credentials dynamically).

🎯 Purpose of the Project


This project was created to:

Apply Java concepts to a real-world use case

Practice structured programming and clean code

Gain confidence in handling user input and program flow

Build a strong foundation for backend development

🚀 Future Enhancements


Persistent storage using file handling or JDBC database (e.g., MySQL / H2)

Multi-user session management with persistent login state

GUI integration using Swing or JavaFX