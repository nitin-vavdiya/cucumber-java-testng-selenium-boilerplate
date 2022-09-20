/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022. smartSense
 *
 */

package com.smartsense.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.mongodb.MongoClientURI;
import com.smartsense.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The type Report manager.
 *
 * @author Nitin
 * @since 02 /03/2022
 */
public final class ReportManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportManager.class);

    private ReportManager() {
    }

    /**
     * Create report extent reports.
     *
     * @return the extent reports
     */
    public static ExtentReports createReport() {
        MongoClientURI uri = new MongoClientURI(Configuration.getMongoDBConnectionString());
        ExtentKlovReporter klov = new ExtentKlovReporter("smartsense-cucumber-testng");
        klov.initKlovServerConnection(Configuration.getKlovServerURL()).initMongoDbConnection(uri);
        ExtentReports extentReport = new ExtentReports();
        extentReport.attachReporter(klov);
        return extentReport;
    }
}
