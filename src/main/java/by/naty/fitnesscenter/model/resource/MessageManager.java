package by.naty.fitnesscenter.model.resource;

import java.util.ResourceBundle;

public class MessageManager {
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
