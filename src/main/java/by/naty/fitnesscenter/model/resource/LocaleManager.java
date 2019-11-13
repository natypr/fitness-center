package by.naty.fitnesscenter.model.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LocaleManager {
    EN(ResourceBundle.getBundle("messages", new Locale("en", "US"))),
    RU(ResourceBundle.getBundle("messages", new Locale("ru", "RU")));

    private ResourceBundle bundle;

    LocaleManager(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getProperty(String key) {
        return bundle.getString(key);
    }

    public static LocaleManager defineLocale(String locale){
        if(locale == null){
            return EN;
        }
        return "ru_RU".equals(locale) ? RU : EN;
    }
}
