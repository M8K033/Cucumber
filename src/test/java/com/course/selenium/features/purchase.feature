Feature: Purchasing a product
  As a user, I want to log in, purchase a product, and finalize the order.

  Scenario: Purchase Hummingbird Printed Sweater
    Given I am on the main page
    When I log in with email "los@los.pl" and password "1234567890!@"
    And I click main button
    And I select the product "Hummingbird Printed Sweater"
    And I choose size M and quantity 5
    And I add the product to the cart
    And I proceed to checkout
    And I proceed to secound checkout
    And I confirm the address
    And I choose the delivery method "PrestaShop pick up in store"
    And I choose the payment method "Pay by Check"
    And I place the order
    Then I should see the confirmation with the total amount