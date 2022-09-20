/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022. smartSense
 *
 */

package com.smartsense.cucumber.runner.login;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

/**
 * The type Login steps runner.
 *
 * @author Nitin
 * @version 1.0
 */
@CucumberOptions(tags = "",
        features = {"src/test/resources/features/login.feature"},
        glue = {"com.smartsense.cucumber.definition.login"},
        plugin = {"com.smartsense.CustomReportListener"})
@Test(groups = {"Login", "Regression"})
public class LoginStepsRunner extends AbstractTestNGCucumberTests {

}