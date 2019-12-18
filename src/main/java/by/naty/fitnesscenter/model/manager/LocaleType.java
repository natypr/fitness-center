package by.naty.fitnesscenter.model.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LocaleType {
    EN(ResourceBundle.getBundle("messages", new Locale("en", "US"))),
    RU(ResourceBundle.getBundle("messages", new Locale("ru", "RU")));

    private ResourceBundle bundle;

    LocaleType(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public static LocaleType defineLocale(String locale) {
        if (locale == null) {
            return EN;
        }
        return "ru_RU".equals(locale) ? RU : EN;
    }

    public String getProperty(String key) {
        return bundle.getString(key);
    }
}
