package by.naty.fitnesscenter.model.logic;

import by.naty.fitnesscenter.model.resource.ConfigurationManager;

public final class DefaultLogic {
    private String path = ConfigurationManager.getProperty("path.page.index");

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
