Feature: Create and verify a new address
  As a user, I want to log in and create a new address to verify if it was saved correctly.

  Scenario: Log in and add a new address
    Given I am on the login page
    When I log in with the email "los@los.pl" and password "1234567890!@"
    And I navigate to the addresses page
    And I create a new address with:
      | alias | address | city | zip code | country        | phone     |
      | Home  | Sucha   | Wwa  | 12345    | United Kingdom | 123456789 |
    Then the new address should be displayed correctly
    Then close browser