/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022. smartSense
 *
 */

package com.smartsense;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Given;
import com.smartsense.config.Configuration;
import com.smartsense.report.ReportManager;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Custom report listener.
 *
 * @author Nitin
 * @version 1.0
 */
public class CustomReportListener implements EventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomReportListener.class);

    private static final ExtentReports REPORT = ReportManager.createReport();
    /**
     * The Feature.
     */
    Map<String, ExtentTest> feature = new HashMap<>();
    /**
     * The Scenario.
     */
    ExtentTest scenario;
    /**
     * The Step.
     */
    ExtentTest step;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, this::runStarted);
        publisher.registerHandlerFor(TestRunFinished.class, this::runFinished);
        publisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
        publisher.registerHandlerFor(TestCaseStarted.class, this::ScenarioStarted);
        publisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::stepFinished);
    }

    private void runStarted(TestRunStarted event) {
        LOGGER.info("1. runStarted");
    }


    private void stepFinished(TestStepFinished event) {
        if (event.getResult().getStatus().toString() == "PASSED") {
            step.log(com.aventstack.extentreports.Status.PASS, "This passed");
        } else if (event.getResult().getStatus().toString() == "SKIPPED") {
            step.log(com.aventstack.extentreports.Status.SKIP, "This step was skipped ");
        } else {
            step.log(Status.FAIL, "This failed");
            step.fail(
                    event.getResult().getError(),
                    MediaEntityBuilder.createScreenCaptureFromPath(
                                    String.format(
                                            "%s%s.png",
                                            Configuration.getScreenShotPath(), event.getTestCase().getId().toString()))
                            .build());
        }
    }

    private void stepStarted(TestStepStarted event) {
        LOGGER.info("stepStarted");
        String stepName = " ";
        String keyword = "Triggered the hook :";
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
            stepName = steps.getStep().getText();
            keyword = steps.getStep().getKeyword();
        } else {
            HookTestStep hoo = (HookTestStep) event.getTestStep();
            stepName = hoo.getHookType().name();
        }
        step = scenario.createNode(Given.class, keyword + " " + stepName);
    }

    private void ScenarioStarted(TestCaseStarted event) {
        LOGGER.info("ScenarioStarted");
        String featureName = event.getTestCase().getUri().toString();
        ExtentTest extentTest = feature.get(featureName);
        scenario = extentTest.createNode(event.getTestCase().getName());
        extentTest.assignCategory(event.getTestCase().getTags().toArray(new String[0]));
    }

    private void featureRead(TestSourceRead event) {
        LOGGER.info("featureRead");
        String featureSource = event.getUri().toString();
        String featureName = featureSource.split(".*/")[1];
        if (feature.get(featureSource) == null) {
            ExtentTest test = REPORT.createTest(featureName);
            feature.putIfAbsent(featureSource, test);
        }
    }


    private void runFinished(TestRunFinished event) {
        REPORT.flush();
        LOGGER.info(" last: runFinished");
    }

}
