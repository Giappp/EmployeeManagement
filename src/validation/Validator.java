package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final String VIETNAMESE_PHONE_REGEX = "^(03|05|07|09)[0-9]{8}";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static final Pattern VIETNAMESE_PHONE_PATTERN = Pattern.compile(VIETNAMESE_PHONE_REGEX);

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        return emailMatcher.matches();
    }

    public static boolean isPhoneValid(String phone) {
        if (phone == null) {
            return false;
        }
        Matcher phoneMatcher = VIETNAMESE_PHONE_PATTERN.matcher(phone);
        return phoneMatcher.matches();
    }

    public static boolean stringNotBlank(String str) {
        return !str.trim().isBlank();
    }
}
