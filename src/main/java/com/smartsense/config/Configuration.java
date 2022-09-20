/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022. smartSense
 *
 */

package com.smartsense.config;

import java.io.File;

/**
 * The type Configuration.
 *
 * @author Nitin
 * @version 1.0
 */
public class Configuration {


    /**
     * Gets screen shot path.
     *
     * @return the screen shot path
     */
    public static String getScreenShotPath() {
        String path = "/tmp/reports/screenshots/";
        File f = new File(path);
        f.mkdirs();
        return path;
    }

    /**
     * Gets mongo db connection string.
     *
     * @return the mongo db connection string
     */
    public static String getMongoDBConnectionString() {
        return "mongodb://<username>:<password>@<host/ip>:<port>";
    }

    /**
     * Gets klov server url.
     *
     * @return the klov server url
     */
    public static String getKlovServerURL() {
        return "klov server url";
    }
}
