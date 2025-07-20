import java.time.LocalDate;

/// Represents a financial transaction with a date, amount, description, and type (INCOME or EXPENSE).
public class Transaction {
    private LocalDate date;
    private double amount;
    private String description;
    private TransactionType type;

    /**
     * Constructs a new Transaction.
     *
     * @param date        The date of the transaction.
     * @param amount      The transaction amount.
     * @param description A brief description of the transaction.
     * @param type        The type of transaction (INCOME or EXPENSE).
     */
    public Transaction(LocalDate date, double amount, String description, TransactionType type) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.type = type;
    }

    /**
     * Returns the transaction date
     * @return The date of the transaction
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the transaction amount
     * @return The amount of the transaction
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the transaction description
     * @return The description of a transaction
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns transaction type
     * @return The type of transaction (Income / Expense)
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Returns a string representation of the transaction
     * @return Format transaction details
     */
    @Override
    public String toString() {
        return date + " | " + type + " | " + amount + " | " + description;
    }
}
