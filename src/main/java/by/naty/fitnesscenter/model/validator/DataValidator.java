package by.naty.fitnesscenter.model.validator;

import java.util.regex.Pattern;

public class DataValidator {
    private final static String USER_NAME_REGEX = "[a-zA-Z]\\w+";
    private static final String EMAIL_REGEX = "^([a-z0-9_.-]+)@([a-z0-9_.-]+)\\.([a-z.]{2,6})$";
    private final static String PASSWORD_REGEX = "\\w+";
    public static final String USER_YEARS_OLD_REGEX = "\\d+";

    public static boolean isNameCorrect(String username){
        return (Pattern.matches(USER_NAME_REGEX, username));
    }

    public static boolean isSurnameCorrect(String surname){
        return Pattern.matches(USER_NAME_REGEX, surname);
    }

    public static boolean isEmailCorrect(String email){
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isPasswordCorrect(String password){
        return Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean isYearsOldCorrect(String yearOld){
        return Pattern.matches(USER_YEARS_OLD_REGEX, yearOld);
    }
}
