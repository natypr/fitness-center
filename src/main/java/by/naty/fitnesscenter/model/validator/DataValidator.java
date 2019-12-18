package by.naty.fitnesscenter.model.validator;

import java.util.regex.Pattern;

public class DataValidator {
    private static final String NAME_REGEX = "^([A-Z][a-z]{2,20})|([А-Я][а-я]{2,20})$";
    private static final String SURNAME_REGEX = "^([A-Z][a-z]{2,30})|([А-Я][а-я]{2,30})$";
    private static final String EMAIL_REGEX = "^([a-z0-9_.-]+)@([a-z0-9_.-]+)\\.([a-z]{2,5})$";
    private static final String PASSWORD_REGEX = "^([A-Za-z0-9_-]{8,40})$";
    private static final String USER_YEARS_OLD_REGEX = "^([1-9][0-9]?)$";

    private static final String NUMBER_OF_WORKOUT_REGEX = "^([1-9][0-9]{1,2})$";
    private static final String EDUCATION_REGEX = "^([A-Za-z ,-_]{3,30})|([А-Яа-я ,-_]{3,30})$";
    private static final String COST_PER_ONE_WORKOUT_REGEX = "^([0-9.]{1,8})$";

    private static final String EQUIPMENT_REGEX = "^([A-Za-z ,-_]{3,40})|([А-Яа-я ,-_]{3,40})$";
    private static final String DESCRIPTION_REGEX = "^([A-Za-z ,-_]{3,100})|([А-Яа-я ,-_]{3,100})$";


    public static boolean isNameCorrect(String username) {
        return (Pattern.matches(NAME_REGEX, username));
    }

    public static boolean isSurnameCorrect(String surname) {
        return Pattern.matches(SURNAME_REGEX, surname);
    }

    public static boolean isEmailCorrect(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isPasswordCorrect(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean isYearsOldCorrect(String yearOld) {
        return Pattern.matches(USER_YEARS_OLD_REGEX, yearOld);
    }

    public static boolean isNumberOfWorkoutCorrect(String numberOfWorkout) {
        return Pattern.matches(NUMBER_OF_WORKOUT_REGEX, numberOfWorkout);
    }

    public static boolean isEducationCorrect(String education) {
        return Pattern.matches(EDUCATION_REGEX, education);
    }

    public static boolean isCostPerOneWorkoutCorrect(String costPerOneWorkout) {
        return Pattern.matches(COST_PER_ONE_WORKOUT_REGEX, costPerOneWorkout);
    }

    public static boolean isEquipmentCorrect(String equipment) {
        return Pattern.matches(EQUIPMENT_REGEX, equipment);
    }

    public static boolean isDescriptionCorrect(String description) {
        return Pattern.matches(DESCRIPTION_REGEX, description);
    }
}
