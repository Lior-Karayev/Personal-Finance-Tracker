import java.util.ArrayList;
import java.util.List;

/// Manages a list of transactions and provides methods for adding and analyzing them
public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * Adds a transaction to the list
     * @param transaction
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Returns the List of all transactions
     * @return A list of all recorded transactions
     */
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    /**
     * Calculate the current balance based on all transactions
     * @return The calculated balance
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


}
