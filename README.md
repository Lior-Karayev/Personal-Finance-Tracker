# Personal Finance Tracker 💰

A simple Java console application that helps users track their income and expenses.

## 📋 Features

- Add income and expense transactions
- View all recorded transactions
- Calculate total balance
- Save and load transactions from file
- Organize transactions by type (INCOME/EXPENSE)

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- IntelliJ IDEA or any Java IDE

### Run Instructions (IntelliJ)

1. Clone the repository or download the source code.
2. Open IntelliJ and create a new Java project.
3. Place all `.java` files in the `src/` folder.
4. Right-click `FinanceTracker.java` → `Run 'FinanceTracker.main()'`

### Run from Terminal

```bash
javac src/*.java
java -cp src FinanceTracker
```

## 🧪 Example Usage
```
--- Personal Finance Tracker ---
1. Add Transaction
2. View Transactions
3. View Balance
4. Exit
```

## 📂 Project Structure
<pre lang="markdown"><code>```text PersonalFinanceTracker/ 
├── README.md 
├── src/
│ ├── FinanceTracker.java // CLI and main loop 
│ ├── Transaction.java // Represents a transaction 
│ ├── TransactionManager.java // Business logic for transactions 
│ ├── FileManager.java // File save/load logic 
│ └── TransactionType.java // Enum for transaction types 
└── transactions.txt // Created automatically to store data ```</code></pre>


## 📝 Data Format (transactions.txt)
```
YYYY-MM-DD|TYPE|AMOUNT|DESCRIPTION
```

## 🔧 Future Improvements
#### - Edit/delete transactions
#### - Filter transactions by date or amount
#### - Monthly reports
#### - Export to CSV
#### - JavaFX or Swing GUI

## 🧑‍💻 Author
### Lior Karaev
#### Software Engineering Student
#### [Github profile](https://github.com/Lior-Karayev)