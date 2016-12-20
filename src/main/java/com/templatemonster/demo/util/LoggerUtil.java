package com.templatemonster.demo.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;

/**
 * Created by i.gonchar on 20.12.2016.
 */
public class LoggerUtil {
    private static String loggerFilePath = "./target/log.log";

    public static Logger createLogger() {
        Logger logger = LogManager.getRootLogger();
        logger.info("Logger was initialized");
        return logger;
    }

    public static void createLogFile(FirefoxProfile profile) throws IOException {
        File outFile = new File(loggerFilePath);

        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        profile.setPreference("webdriver.log.driver", "DEBUG");
        profile.setPreference("webdriver.log.file", loggerFilePath);
    }

}
