# 💸 Personal Finance Tracker

A clean and scalable **Java-based CLI application** designed to help users manage their personal finances with ease. Built with modular design principles, this project demonstrates **code reusability**, **input validation**, and **file-based persistence**, making it ideal for both personal use and as part of a professional portfolio.

## 📂 Cloning the Repository

```bash
git clone https://github.com/Lior-Karayev/Personal-Finance-Tracker.git
cd Personal-Finance-Tracker
```

> ⚙️ Java 17 or later is recommended. This project uses only core Java libraries — no external dependencies required.

---

## 🧠 Features

- 🔁 **Add, view, and filter transactions**
- 🗃️ **Save and load transactions to/from a file**
- 📊 **Calculate current balance automatically**
- 🔎 **Filter by:**
    - Type (Income / Expense)
    - Date Range (supports full day or date+time input)
    - Amount Range
    - Category (Food, Transport, Salary, etc.)
    - Keyword in description
- 🧱 **Scalable architecture** — utilities, enums, and managers are separated for maintainability
- ✅ **Robust input validation** — protects against crashes or invalid input
- 🧪 Built-in structure for future improvements (unit testing, database storage, GUI interface, etc.)

---

## 🏗️ Tech Stack

| Tool        | Purpose                          |
|-------------|----------------------------------|
| Java        | Core application logic           |
| Javadoc     | Auto-generating documentation    |
| IntelliJ/VSCode | Recommended development IDE |

---

## 🧩 Project Structure

```
src/
├── main/
│   ├── FileManager.java
│   ├── MenuManager.java
│   ├── PersonalFinanceTracker.java
│   ├── Transaction.java
│   ├── TransactionManager.java
│   ├── TransactionType.java
│   └── Category.java
├── Utils/
│   ├── InputUtils.java
│   └── EnumUtils.java
```

---

## 📌 How to Run

You can compile and run it directly from the terminal:

```bash
javac -d out src/**/*.java
java -cp out main.PersonalFinanceTracker
```

---

## 📚 Documentation

To generate documentation with Javadoc:

```bash
javadoc -d docs -sourcepath src Utils main
```

Then open `docs/index.html` in your browser to explore the generated documentation.

---

## 📈 Future Improvements

- GUI interface with JavaFX or Swing
- Integration with a real database (e.g., SQLite)
- Report export to PDF or CSV
- Category budgets and alerts

---

## 🙋‍♂️ Author

**Lior Karaev**  
🔗 [GitHub Profile](https://github.com/Lior-Karayev)

---

> ✅ This project is part of my personal software engineering portfolio.  
Feel free to use it as a reference or fork it to explore more enhancements.
