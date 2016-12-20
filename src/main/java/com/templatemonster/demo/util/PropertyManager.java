package com.templatemonster.demo.util;

import java.util.Properties;

public class PropertyManager extends BaseUtils{
    private Properties properties = new Properties();

    public void generateProperty() {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("properties/environment.properties"));
            LOGGER.info("properties were generated from file: test/resources/properties/environment.properties");
        } catch (Exception e) {
            LOGGER.error("Failed to load properties:" + e.getMessage());
        }
    }

    public String getProperty(String propertyKey) {
        if (!properties.containsKey(propertyKey)) {
            LOGGER.debug("No property key was passed");
            return null;
        }
        if (properties.getProperty(propertyKey).equals("")) {
            LOGGER.info("Empty property key was passed");
            return null;
        }

        return properties.getProperty(propertyKey);
    }
}
