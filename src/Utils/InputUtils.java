package Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Supplier;
import main.TransactionType;

/**
 * Utility class for handling user input and input validation.
 * This class provides reusable methods for reading and validating input like dates and transaction types.
 */
public class InputUtils {

    // Private constructor to prevent instantiation
    private InputUtils() {}

    /**
     * Prompts the user to choose a transaction type (Income or Expense) and validates the input.
     * Keeps prompting until a valid choice is entered.
     *
     * @param scanner the Scanner object used for reading user input
     * @return the selected {@link TransactionType}
     */
    public static TransactionType readType(Scanner scanner) {
        return repeatUntilNotNull(() -> {
            TransactionType type;
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

            return type;
        });
    }

    /**
     * Reads a date from user input and returns it as a {@link LocalDateTime}.
     * Accepts either full datetime format {@code yyyy-MM-dd HH:mm} or date-only format {@code yyyy-MM-dd}.
     * If only a date is provided, time defaults to start of day.
     *
     * @param scanner the Scanner object used for reading input
     * @param msg the prompt message to show the user
     * @return the parsed {@link LocalDateTime}
     */
    public static LocalDateTime readDate(Scanner scanner, String msg) {
        return readDate(scanner, msg, false);
    }

    /**
     * Reads a date (with optional time) from the user and converts it into {@link LocalDateTime}.
     * If only the date is provided and {@code isWholeDay} is true, the time is set to 23:59.
     * Otherwise, it defaults to the start of the day (00:00).
     *
     * @param scanner the Scanner object used for reading input
     * @param msg the prompt message to show the user
     * @param isWholeDay if true and input is date-only, sets time to end of day (23:59), otherwise to start of day
     * @return the parsed {@link LocalDateTime}
     */
    public static LocalDateTime readDate(Scanner scanner, String msg, boolean isWholeDay) {
        return repeatUntilNotNull(() -> {
            LocalDateTime time;
            System.out.print(msg);
            String input = scanner.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                time = LocalDateTime.parse(input, formatter);
            } catch (Exception e1) {
                try {
                    DateTimeFormatter dateOnly = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(input, dateOnly);
                    time = isWholeDay ? date.atTime(23, 59) : date.atStartOfDay();
                } catch (Exception e2) {
                    System.out.println("Error: Invalid time format. Please enter time format (yyyy-MM-dd HH:mm");
                    time = null;
                }
            }

            return time;
        });
    }

    /**
     * Repeatedly executes the given {@link Supplier} until it returns a non-null result.
     * <p>
     * This method is useful for validating user input or retrying operations until a valid value is obtained.
     * </p>
     *
     * @param inputSupplier the supplier function that provides a value of type T;
     *                      it should return {@code null} when the input is invalid
     * @param <T> the type of the result
     * @return the first non-null result returned by the supplier
     */
    public static <T> T repeatUntilNotNull(Supplier<T> inputSupplier) {
        T result = null;
        do {
            result = inputSupplier.get();
        } while (result == null);

        return result;
    }
}
