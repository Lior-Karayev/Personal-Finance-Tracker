package main;

import java.util.Scanner;

/**
 * Entry point for the Personal Finance Tracker application.
 * This class manages the main program loop, displaying the menu and
 * delegating tasks to the appropriate handlers such as adding transactions,
 * viewing them, and filtering.
 */
public class PersonalFinanceTracker {

    /**
     * The main method that launches the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager transactionManager = new TransactionManager();

        // Load transactions from file into memory
        transactionManager.getAllTransactions().addAll(FileManager.loadTransactions());
        int choice = 0;

        while(true) {
            MenuManager.showMenu();

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    MenuManager.addTransaction(transactionManager, scanner);
                }
                case 2 -> {
                    MenuManager.printTransactionsHistory(transactionManager.getAllTransactions());
                }
                case 3 -> {
                    MenuManager.printBalance(transactionManager);
                }
                case 4 -> {
                    MenuManager.filterTransactions(transactionManager);
                }
                case 5 -> {
                    MenuManager.exitMenu(transactionManager);
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
