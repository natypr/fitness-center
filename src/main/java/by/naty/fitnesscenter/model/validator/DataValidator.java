package by.naty.fitnesscenter.model.validator;

import java.util.regex.Pattern;

public class DataValidator {
    private static final String NAME_REGEX = "[a-zA-Zа-яА-Я]{1,20}";
    private static final String SURNAME_REGEX = "[a-zA-Zа-яА-Я]{1,30}";
    private static final String EMAIL_REGEX = "^([a-z0-9_.-]+)@([a-z0-9_.-]+)\\.([a-z]{2,6})$";
    private static final String PASSWORD_REGEX = "[a-zA-Z_0-9]{8,40}";
    private static final String USER_YEARS_OLD_REGEX = "[1-9][0-9]?";

    public static boolean isNameCorrect(String username){
        return (Pattern.matches(NAME_REGEX, username));
    }

    public static boolean isSurnameCorrect(String surname){
        return Pattern.matches(SURNAME_REGEX, surname);
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
