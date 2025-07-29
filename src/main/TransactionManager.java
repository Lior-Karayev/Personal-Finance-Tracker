package main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of financial transactions and provides methods
 * for adding, retrieving, and analyzing them (e.g., filtering and calculating balance).
 */
public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * Adds a transaction to the internal transaction list.
     *
     * @param transaction The transaction to be added.
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Returns the list of all recorded transactions.
     *
     * @return A list of transactions.
     */
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    /**
     * Calculates the current balance by summing income and subtracting expenses.
     *
     * @return The calculated balance.
     */
    public double getBalance() {
        double balance = 0;
        for(Transaction t: transactions) {
            if(t.getType() == TransactionType.INCOME) {
                balance += t.getAmount();
            } else {
                balance -= t.getAmount();
            }
        }

        return balance;
    }

    /**
     * Filters transactions by their type (INCOME or EXPENSE).
     *
     * @param type The transaction type to filter by.
     * @return A list of transactions of the given type.
     */
    public ArrayList<Transaction> filterByType(TransactionType type){
        ArrayList<Transaction> filteredList = new ArrayList<>();
        for(Transaction t: transactions) {
            if(t.getType() == type) {
                filteredList.add(t);
            }
        }

        return filteredList;
    }

    /**
     * Filters transactions that occurred within a specific date and time range.
     *
     * @param from The start of the date range (inclusive).
     * @param to   The end of the date range (inclusive).
     * @return A list of transactions within the specified range.
     */
    public ArrayList<Transaction> filterByDateRange(LocalDateTime from, LocalDateTime to) {
        ArrayList<Transaction> filteredList = new ArrayList<>();
        for(Transaction t: transactions) {
            LocalDateTime date = t.getDate();
            if((date.isEqual(from) || date.isAfter(from)) && (date.isEqual(to) || date.isBefore(to))){
                filteredList.add(t);
            }
        }

        return filteredList;
    }

    /**
     * Filters transactions within a specified amount range.
     *
     * @param from The minimum amount (inclusive).
     * @param to   The maximum amount (inclusive).
     * @return A list of transactions whose amounts fall within the range.
     */
    public ArrayList<Transaction> filterByAmountRange(double from, double to) {
        ArrayList<Transaction> filteredList = new ArrayList<>();

        for(Transaction t: transactions) {
            if(t.getAmount() >= from && t.getAmount() <= to) {
                filteredList.add(t);
            }
        }

        return filteredList;
    }

    /**
     * Filters transactions by a specific category.
     *
     * @param category The category to filter by.
     * @return A list of transactions that match the specified category.
     */
    public ArrayList<Transaction> filterByCategory(Category category) {
        ArrayList<Transaction> filteredList = new ArrayList<>();

        for(Transaction t: transactions) {
            if(t.getCategory() == category) {
                filteredList.add(t);
            }
        }

        return filteredList;
    }

    /**
     * Filters transactions based on a keyword found in the description.
     *
     * @param keyword The keyword to search for (case-insensitive).
     * @return A list of transactions whose description contains the keyword.
     */
    public ArrayList<Transaction> filterByKeyword(String keyword) {
        ArrayList<Transaction> filteredList = new ArrayList<>();
        for(Transaction t: transactions) {
            if(t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                filteredList.add(t);
        }

        return filteredList;
    }


}
