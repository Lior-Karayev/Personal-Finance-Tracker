package Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for working with enums.
 */
public class EnumUtils {

    /**
     * Returns a list of capitalized string representations of the enum constants in the given enum class.
     * For example, if the enum constant is {@code SALARY}, the result will contain {@code "Salary"}.
     *
     * @param enumClass The enum class to process.
     * @param <E>       The enum type.
     * @return A list of capitalized enum names as strings.
     */
    public static <E extends Enum<E>> List<String> getCapitalizedEnumStrings(Class<E> enumClass) {
        List<String> result = new ArrayList<>();
        for (E constant: enumClass.getEnumConstants()) {
            String name = constant.name().toLowerCase();
            String capitalized = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            result.add(capitalized);
        }

        return result;
    }
}
