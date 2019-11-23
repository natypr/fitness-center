package by.naty.fitnesscenter.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertyLoader {
    private static final Logger LOG = LogManager.getLogger();

    Properties loadFile(String fileName) {
        Properties properties = new Properties();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            LOG.error("Error loading file: ", e);
        }
        return properties;
    }
}