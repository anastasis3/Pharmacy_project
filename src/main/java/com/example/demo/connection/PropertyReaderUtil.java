package com.example.demo.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReaderUtil {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertyReaderUtil.class.getClassLoader().getResourceAsStream("db/db.properties")) {
            if (input == null) {
                throw new IOException("Database properties file not found.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}