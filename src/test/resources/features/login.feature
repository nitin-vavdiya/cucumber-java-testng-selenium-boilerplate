Feature: Login to my application

  @login @RegressionTest
  Scenario: Login with valid username and password
    Given Open my application
    When add username
    When add password
    When click on login
    Then Home page should be open