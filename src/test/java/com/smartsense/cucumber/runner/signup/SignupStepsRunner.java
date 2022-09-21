/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022. smartSense
 *
 */

package com.smartsense.cucumber.runner.signup;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

/**
 * The type Signup steps runner.
 *
 * @author Nitin
 * @version 1.0
 */
@CucumberOptions(tags = "",
        features = {"src/test/resources/features/signup.feature"},
        glue = {"com.smartsense.cucumber.definition.signup"},
        plugin = {"pretty", "com.epam.reportportal.cucumber.StepReporter"})
@Test(groups = {"Signup", "Regression"})
public class SignupStepsRunner extends AbstractTestNGCucumberTests {

}
