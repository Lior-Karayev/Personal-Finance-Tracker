import java.time.LocalDate;
import java.util.Scanner;

public class PersonalFinanceTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager transactionManager = new TransactionManager();
        transactionManager.getAllTransactions().addAll(FileManager.loadTransactions());
        int choice = 0;

        while(true) {
            System.out.println("\n------------ Personal Finance Tracker ------------");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. View Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter a description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    TransactionType type;
                    do{
                        System.out.print("Choose income or expense (I/E): ");
                        char typeChar = scanner.nextLine().charAt(0);

                        if(typeChar == 'I' || typeChar == 'i'){
                            type = TransactionType.INCOME;
                        } else if(typeChar == 'E' || typeChar == 'e'){
                            type = TransactionType.EXPENSE;
                        } else {
                            System.out.println("Wrong input please enter a valid choice (I/E): ");
                            type = null;
                        }
                    } while(type == null);

                    Transaction transaction = new Transaction(LocalDate.now(), amount, description, type);
                    transactionManager.addTransaction(transaction);
                    System.out.println("Transaction added successfully!");
                }
                case 2 -> {
                    System.out.println("------------ Transaction History ------------");
                    for(Transaction transaction: transactionManager.getAllTransactions()){
                        System.out.println(transaction);
                    }
                }
                case 3 -> {
                    System.out.printf("Your current balance is: %.2f%n", transactionManager.getBalance());
                }
                case 4 -> {
                    FileManager.saveTransactions(transactionManager.getAllTransactions());
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
