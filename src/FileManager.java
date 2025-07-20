import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/// Handles saving and loading transactions from a text file
public class FileManager {
    private static final String FILE_NAME = "transactions.txt";

    /**
     * Saves all transactions to a file.
     * @param transactions
     */
    public static void saveTransactions(List<Transaction> transactions) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Transaction t: transactions) {
                String line = t.getDate() + "|" + t.getType() + "|" + t.getAmount() + "|" +t.getDescription();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    /**
     * Loads all transactions from a file.
     * @return A list of loaded transactions, or an empty list if the file doesn't exist or fails to load
     */
    public static List<Transaction> loadTransactions(){
        List<Transaction> transactions = new ArrayList<>();

        File file = new File(FILE_NAME);
        if(!file.exists()) return transactions;

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if(parts.length != 4) continue;

                LocalDate date = LocalDate.parse(parts[0]);
                TransactionType type = TransactionType.valueOf(parts[1]);
                double amount = Double.parseDouble(parts[2]);
                String description = parts[3];

                transactions.add(new Transaction(date, amount, description, type));
            }
        }catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }

        return transactions;
    }
}
