package by.naty.fitnesscenter.model.resource;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config");

    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
