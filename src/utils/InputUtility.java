package utils;

import constants.Messages;
import enums.Status;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Function;
import java.util.function.Predicate;

public class InputUtility {
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static Status getStatus(String prompt) {
        while (true) {
            System.out.println(MessageUtility.getStatusMessage());
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
                System.out.println(Messages.Error.INVALID_VALUE);
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
                }
            } catch (Exception ignored) {

            }
            System.out.println(errorMessage);
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
