/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2022. smartSense
 *
 */

package com.smartsense.cucumber.definition.login;

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
import org.testng.Assert;

import java.time.Duration;

/**
 * The type Login steps.
 *
 * @author Nitin
 * @version 1.0
 */
public class LoginSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSteps.class);

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
     * Open my application.
     */
    @Given("Open my application")
    public void openMyApplication() {
        LOGGER.debug("openMyApplication");
        driver.get("https://google.com/");
    }

    /**
     * Add username.
     */
    @When("add username")
    public void addUsername() {
        LOGGER.debug("addUsername");
        //Assert.fail();
    }

    /**
     * Add password.
     */
    @When("add password")
    public void addPassword() {
        LOGGER.debug("addPassword");
    }

    /**
     * Click on login.
     */
    @When("click on login")
    public void clickOnLogin() {
        LOGGER.debug("clickOnLogin");
        Assert.fail();
    }

    /**
     * Home page should be open.
     */
    @Then("Home page should be open")
    public void homePageShouldBeOpen() {
        LOGGER.debug("homePageShouldBeOpen");
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
