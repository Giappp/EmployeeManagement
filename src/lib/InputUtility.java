package lib;

import constants.Config;
import constants.Messages;
import enums.ChoiceMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;

public class InputUtility {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

    public static <E extends ChoiceMapper> E getChoice(Class<E> choices) {
        while (true) {
            MenuUtility.choiceMenu(choices);
            int userInput = getNumber(Messages.Prompt.CHOICE, Integer::parseInt);
            for (E value : choices.getEnumConstants()) {
                if (value.getChoice() == userInput) {
                    return value;
                }
            }
            System.out.println(Messages.Error.INVALID_CHOICE);
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
}
