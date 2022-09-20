/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022. smartSense
 *
 */

package com.smartsense.cucumber.definition.signup;

import com.smartsense.utils.CommonUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * The type Signup steps.
 *
 * @author Nitin
 * @version 1.0
 */
public class SignupSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignupSteps.class);

    private static WebDriver driver;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        CommonUtils.driverMap.put(getClass().getName(), driver);
    }

    /**
     * Open sign up page.
     */
    @Given("Open signup page")
    public void openSignUpPage() {
        LOGGER.debug("openSignUpPage");
        driver.get("https://google.com/");
    }

    /**
     * Add email.
     */
    @When("add email")
    public void addEmail() {
        LOGGER.debug("addEmail");
        //Assert.fail();
    }

    /**
     * Add firstname.
     */
    @When("add firstname")
    public void addFirstname() {
        LOGGER.debug("addFirstname");
    }

    /**
     * Add lastname.
     */
    @When("add lastname")
    public void addLastname() {
        LOGGER.debug("addLastname");
        // Assert.fail();
    }

    /**
     * Signup success.
     */
    @Then("Signup success")
    public void signupSuccess() {
        LOGGER.debug("success");
    }

    /**
     * Teardown.
     *
     * @param scenario the scenario
     */
    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            CommonUtils.takeScreenshot(driver, scenario.getId());
        }
        driver.quit();
    }
}
