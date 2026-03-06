package lib;

import constants.Config;
import constants.Messages;
import enums.Status;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;

public class InputUtility {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static Status getStatus(String prompt) {
        while (true) {
            MenuUtility.statusMenu();
            int choice = getNumber(prompt, Integer::parseInt);
            if (choice >= Status.values().length) {
                System.out.println(Messages.Error.INVALID_VALUE);
                continue;
            }
            return Status.values()[choice];
        }
    }

    public static <T extends Number> T getNumber(String prompt, Function<String, T> parser) {
        while (true) {
            System.out.print(prompt);
            try {
                return parser.apply(br.readLine());
            } catch (Exception e) {
                System.out.println(Messages.Error.INVALID_NUMBER);
            }
        }
    }

    public static String getValidInput(String prompt, Predicate<String> validation, String errorMessage) {
        while (true) {
            System.out.println(prompt);
            try {
                String input = br.readLine();
                if (validation.test(input)) {
                    return input;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (Exception ignored) {
                System.out.println(errorMessage);
            }
        }
    }

    public static String getString(String prompt) {
        System.out.println(prompt);
        try {
            String input = br.readLine();
            return input.trim().isBlank() ? null : input;
        } catch (Exception ignored) {
            return null;
        }
    }

    public static LocalDate getDate(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                String input = br.readLine();
                return LocalDate.parse(input, Config.formatter);
            } catch (Exception ignored) {
                System.out.println(Messages.Error.INVALID_DATE);
            }
        }
    }

    public static <T> T getBaseInput(String prompt, Predicate<T> validator, Function<String, T> parser, String errorMessage) {
        while (true) {
            try {
                String input = br.readLine();
                T value = parser.apply(input);
                if (validator.test(value)) {
                    return value;
                }
            } catch (Exception e) {

            }
            System.out.println(errorMessage);
        }
    }
}
