package main;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading {@link Transaction} data to and from a file.
 * This class provides static methods to persist financial transactions and restore them on startup.
 */
public class FileManager {
    /** The name of the file used to store transaction data. */
    private static final String FILE_NAME = "transactions.txt";

    /**
     * Saves a list of transactions to a file.
     *
     * Each transaction is written as a line with pipe-separated values:
     * <pre>[id]|[date]|[type]|[amount]|[category]|[description]</pre>
     *
     * @param transactions the list of transactions to be saved
     */
    public static void saveTransactions(List<Transaction> transactions) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Transaction t: transactions) {
                String line = t.getId() + "|" + t.getDate() + "|" + t.getType() + "|" + t.getAmount() + "|" + t.getCategory() + "|" +t.getDescription();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    /**
     * Loads transactions from the file.
     *
     * @return a list of {@link Transaction} objects reconstructed from the saved file;
     *         if the file doesn't exist or is corrupted, returns an empty list.
     */
    public static List<Transaction> loadTransactions(){
        List<Transaction> transactions = new ArrayList<>();

        File file = new File(FILE_NAME);
        if(!file.exists()) return transactions;

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length != 6) continue;

                String id = parts[0];
                LocalDateTime date = LocalDateTime.parse(parts[1]);
                TransactionType type = TransactionType.valueOf(parts[2]);
                double amount = Double.parseDouble(parts[3]);
                Category category = Category.valueOf(parts[4]);
                String description = parts[5];

                transactions.add(new Transaction(date, amount, description, type, category));
            }
        }catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }

        return transactions;
    }
}
