package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a financial transaction with details such as date, amount, description, type, and category.
 */
public class Transaction {
    private LocalDateTime timeStamp;
    private double amount;
    private String description;
    private TransactionType type;
    private final String id;
    private static final AtomicInteger counter = new AtomicInteger(1);
    private Category category;

    /**
     * Constructs a new Transaction.
     *
     * @param timeStamp   The timestamp of the transaction.
     * @param amount      The monetary value of the transaction.
     * @param description A brief description of the transaction.
     * @param type        The type of transaction (INCOME or EXPENSE).
     * @param category    The category assigned to the transaction.
     */
    public Transaction(LocalDateTime timeStamp, double amount, String description, TransactionType type, Category category) {
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.description = description;
        this.type = type;
        id = generateId(timeStamp);
        this.category = category;
    }

    /**
     * Generates a unique transaction ID using the timestamp and a counter.
     *
     * @param time The timestamp of the transaction.
     * @return A unique ID string in the format: TX-yyMMdd-HHmmss-N
     */
    private String generateId(LocalDateTime time) {
        DateTimeFormatter datePart = DateTimeFormatter.ofPattern("yyMMdd-HHmmss");
        return "TX-" + time.format(datePart) + "-" + counter.getAndIncrement();
    }

    /**
     * Gets the unique ID of this transaction.
     *
     * @return The transaction ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the timestamp of this transaction.
     *
     * @return The transaction date and time.
     */
    public LocalDateTime getDate() {
        return timeStamp;
    }

    /**
     * Gets the amount of this transaction.
     *
     * @return The monetary amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the description of this transaction.
     *
     * @return A short description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the type of this transaction.
     *
     * @return INCOME or EXPENSE.
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Gets the category of this transaction.
     *
     * @return The transaction's category.
     */
    public Category getCategory() {return category;}

    /**
     * Returns a formatted string representation of the transaction.
     *
     * @return A summary of the transaction details.
     */
    @Override
    public String toString() {
        return "[" + id + "] " + timeStamp.toLocalDate() + " | " + type + " | " + amount + " | " + category + " | " + description;
    }
}
