package utils;

import model.enums.Status;
import validation.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class InputUtility {
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String getString(String message) {
        while (true) {
            System.out.print(message);
            try {
                return br.readLine();
            } catch (IOException ioException) {
                System.out.println("Lỗi Input! Hãy thử lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getEmail(String message) {
        while (true) {
            String email = getString(message);
            if (Validator.isEmailValid(email)) return email;
            System.out.println("Email không hợp lệ");
        }
    }

    public static String getPhone(String message) {
        while (true) {
            String phone = getString(message);
            if (Validator.isPhoneValid(phone)) return phone;
            System.out.println("Số điên thoại không hợp lệ");
        }
    }

    public static Status getStatus(String message) {
        while (true) {
            System.out.println(MessageUtility.getStatusMessage());
            int choice = getNumber(message, Integer::parseInt);
            if (choice >= Status.values().length) {
                System.out.println("Giá trị không hợp lệ");
                continue;
            }
            return Status.values()[choice];
        }
    }

    public static <T extends Number> T getNumber(String message, Function<String, T> parser) {
        while (true) {
            System.out.print(message);
            try {
                return parser.apply(br.readLine());
            } catch (Exception e) {
                System.out.println("Invalid number!");
            }
        }
    }
}
