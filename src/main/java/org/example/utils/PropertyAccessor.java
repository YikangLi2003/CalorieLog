package org.example.utils;

import java.util.Properties;
import java.io.InputStream;

public class PropertyAccessor {
    private static final String CONFIG_FILE_PATH = "config.properties";
    private static final PropertyAccessor INSTANCE = new PropertyAccessor();

    private final Properties properties;

    private PropertyAccessor() {
        properties = new Properties();

        try (
                InputStream input = PropertyAccessor.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH)
        ) {
            if (input == null) {
                throw new RuntimeException("Unable to find properties file: " + CONFIG_FILE_PATH);
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error loading properties file via class loader: " + e.getMessage(), e);
        }
    }

    public static PropertyAccessor getInstance() {
        return INSTANCE;
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException(String.format("No such key '%s' in configuration properties file.", key));
        }

        return properties.getProperty(key);
    }
}
