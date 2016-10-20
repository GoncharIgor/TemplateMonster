package com.templatemonster.demo.util;

import org.testng.log4testng.Logger;

import java.util.Properties;

/**
 * Created by i.gonchar on 28.09.2016.
 */
public class PropertyManager {
    private static final Logger LOGGER = Logger.getLogger(WaitHelper.class);
    private Properties properties = new Properties();

    public void generateProperty() {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("properties/environment.properties"));
        } catch (Exception e) {
            LOGGER.info("Failed to load properties:" + e.getMessage());
        }
    }

    public String getProperty(String propertyKey) {
        if (!properties.containsKey(propertyKey)) {
            return null;
        }
        if (properties.getProperty(propertyKey).equals("")) {
            return null;
        }

        return properties.getProperty(propertyKey);
    }
}
