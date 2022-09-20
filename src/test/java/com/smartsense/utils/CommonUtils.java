/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022. smartSense
 *
 */

package com.smartsense.utils;

import com.google.common.io.Files;
import com.smartsense.config.Configuration;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Common utils.
 *
 * @author Nitin
 * @version 1.0
 */
public class CommonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * The constant driverMap.
     */
    public static final Map<String, WebDriver> driverMap;

    static {
        driverMap = new ConcurrentHashMap<>();
    }

    /**
     * Take screenshot string.
     *
     * @param driver     the driver
     * @param methodName the method name
     * @return the string
     */
    public static String takeScreenshot(WebDriver driver, String methodName) {
        LOGGER.info("====taking screenshot=======for " + methodName);
        String imgPath = null;
        try {
            Thread.sleep(3000);
            String failureImageName = methodName + ".png";
            File failureImageFile = new File(Configuration.getScreenShotPath() + failureImageName);
            imgPath = failureImageFile.getPath();
            failureImageFile.getParentFile().mkdir();
            if (failureImageFile.exists()) {
                //failureImageFile.delete();
                return imgPath;
            }
            File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(imageFile, failureImageFile);
            Reporter.log("Screen saved");
        } catch (Exception e) {
            e.printStackTrace();
            Reporter.log("Can not take screenshot  error - >" + e.getMessage());
            LOGGER.error("Can not take screenshot", e);
        }
        System.out.println("Screenshot saved ===>" + imgPath);
        return imgPath;
    }
}
