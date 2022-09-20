Feature: Signup to foster

  @Signup @RegressionTest
  Scenario: Signup with new email
    Given Open signup page
    When add email
    When add firstname
    When add lastname
    Then Signup success