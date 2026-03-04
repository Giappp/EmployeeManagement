package utils;

import java.time.LocalDate;

public class ConverterUtility {
    public static Object convertToFieldType(String input, Class<?> type) {
        if (type == String.class) return input;
        if (type == Double.class) return Double.parseDouble(input);
        if (type == Long.class) return Long.parseLong(input);
        if (type == Integer.class) return Integer.parseInt(input);
        if (type == LocalDate.class) return LocalDate.parse(input);

        return null;
    }
}
