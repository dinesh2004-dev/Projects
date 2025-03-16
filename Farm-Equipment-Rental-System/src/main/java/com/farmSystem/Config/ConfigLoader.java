package com.farmSystem.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties;
    
    static {
        try {
            properties = new Properties();
            FileInputStream input = new FileInputStream("config.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            System.err.println("Failed to load configuration: " + e.getMessage());
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
