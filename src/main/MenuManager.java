package main;

import Utils.EnumUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static Utils.InputUtils.*;

/**
 * Provides the main user interface for interacting with the Personal Finance Tracker.
 * Handles transaction input, display, and filtering via a command-line menu.
 */
public class MenuManager {

    /**
     * Displays the main menu options to the user.
     */
    public static void showMenu() {
        System.out.println("\n------------ Personal Finance Tracker ------------");
        System.out.println("1. Add main.Transaction");
        System.out.println("2. View Transactions");
        System.out.println("3. View Balance");
        System.out.println("4. Filter Transactions");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Handles user input for adding a new transaction.
     *
     * @param transactionManager the transaction manager responsible for storing the new transaction
     * @param scanner the scanner used for user input
     */
    public static void addTransaction(TransactionManager transactionManager, Scanner scanner) {
        System.out.print("Enter a description: ");
        String description = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        TransactionType type;
        Category category = null;

        // Read transaction type
        do {
            System.out.print("Choose income or expense (I/E): ");
            char typeChar = scanner.nextLine().charAt(0);

            if (typeChar == 'I' || typeChar == 'i') {
                type = TransactionType.INCOME;
            } else if (typeChar == 'E' || typeChar == 'e') {
                type = TransactionType.EXPENSE;
            } else {
                System.out.println("Wrong input please enter a valid choice (I/E): ");
                type = null;
            }
        } while (type == null);

        // Read category
        do {
            System.out.println("\nChoose a category (1 - " + Category.values().length + "): ");
            System.out.println("1. Food");
            System.out.println("2. Transport");
            System.out.println("3. Utilities");
            System.out.println("4. Salary");
            System.out.println("5. Entertainment");
            System.out.println("6. Health");
            System.out.println("7. Other");
            try {
                int choice = scanner.nextInt();

                if (choice >= 1 && choice <= Category.values().length) {
                    category = Category.values()[choice - 1];
                } else {
                    System.out.println("Invalid category number.");
                }

            } catch (Exception e) {
                System.out.println("Please enter a valid choice (1 - " + Category.values().length + ")");
            } finally {
                scanner.nextLine();
            }
        } while (category == null);

        Transaction transaction = new Transaction(LocalDateTime.now(), amount, description, type, category);
        transactionManager.addTransaction(transaction);
        System.out.println("main.Transaction added successfully!");
    }

    /**
     * Displays all transactions from a given list.
     *
     * @param transactions list of transactions to print
     */
    public static void printTransactionsHistory(List<Transaction> transactions) {
        System.out.println("------------ main.Transaction History ------------");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    /**
     * Prints the current balance calculated from all transactions.
     *
     * @param transactionManager the manager containing all transaction records
     */
    public static void printBalance(TransactionManager transactionManager) {
        System.out.printf("Your current balance is: %.2f%n", transactionManager.getBalance());
    }

    /**
     * Saves all transactions and exits the program.
     *
     * @param transactionManager the manager from which transactions will be saved
     */
    public static void exitMenu(TransactionManager transactionManager) {
        FileManager.saveTransactions(transactionManager.getAllTransactions());
        System.out.println("Goodbye!");
    }

    /**
     * Displays a list of categories from the {@link Category} enum.
     * Used for category selection and filtering.
     */
    private static void printCategoryList() {
        List<String> menuItems = EnumUtils.getCapitalizedEnumStrings(Category.class);
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i));
        }
    }

    /**
     * Allows the user to filter transactions by various criteria such as type, date range, amount range,
     * category, or keyword.
     *
     * @param transactionManager the manager from which transactions will be filtered
     */
    public static void filterTransactions(TransactionManager transactionManager) {
        System.out.println("\n------------ Filtered Transactions ------------");
        System.out.println("Filter By:");
        System.out.println("1. Type (INCOME/EXPENSE)");
        System.out.println("2. Date Range");
        System.out.println("3. Amount Range");
        System.out.println("4. Category");
        System.out.println("5. Description Keyword");

        Scanner scanner = new Scanner(System.in);
        int filterChoice = repeatUntilNotNull(() -> {
            Integer choice = null;
            try {
                choice = scanner.nextInt();
                if(choice < 1 || choice > 5) {
                    throw new Exception("Invalid input");
                }
            } catch (Exception e) {
                System.out.print("Invalid input. Please try again (1 - 5): ");
                choice = null;
            } finally {
                scanner.nextLine();
            }

            return choice;
        });

        switch (filterChoice) {
            case 1 -> {
                TransactionType type = readType(scanner);
                List<Transaction> result = transactionManager.filterByType(type);
                result.forEach(System.out::println);
            }
            case 2 -> {
                LocalDateTime from = readDate(scanner, "Enter start date & time (yyyy-MM-dd HH:mm): ");
                LocalDateTime to = readDate(scanner, "Enter end date & time (yyyy-MM-dd HH:mm): ", true);
                List<Transaction> result = transactionManager.filterByDateRange(from, to);
                result.forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Enter min amount: ");
                double fromAmount = scanner.nextDouble();
                System.out.print("Enter max amount: ");
                double toAmount = scanner.nextDouble();
                List<Transaction> result = transactionManager.filterByAmountRange(fromAmount, toAmount);
                result.forEach(System.out::println);
            }
            case 4 -> {
                System.out.println("Filter by:");
                printCategoryList();
                Category category =  repeatUntilNotNull(() -> {
                    Category result = null;
                    try {
                        int choice = scanner.nextInt();
                        if(choice <= 0 || choice > Category.values().length) {
                            System.out.print("Invalid choice. Please try again: ");
                        }
                        result = Category.values()[choice-1];
                    } catch (Exception e) {
                        System.out.print("Bad input please try again: ");
                    } finally {
                        scanner.nextLine();
                    }
                    return result;
                });
                transactionManager.filterByCategory(category).forEach(System.out::println);
            }
            case 5 -> {
                System.out.print("Enter keyword: ");
                String keyword = scanner.nextLine();
                List<Transaction> result = transactionManager.filterByKeyword(keyword);
                result.forEach(System.out::println);
            }
            default -> System.out.println("Invalid filter option.");
        }
    }
}
