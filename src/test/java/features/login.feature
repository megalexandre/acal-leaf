Feature: Login

  Scenario: Login successful

    Given I am on the login screen
    And I enter the username "alexandre"
    And I enter the password "senha"
    When I click the Login button
    And the login screen should be closed
