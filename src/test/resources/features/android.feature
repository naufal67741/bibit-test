Feature: Purchase flow on SauceLab Android App

  Scenario: User completes a successful purchase with sorting
    Given the user launches the app
    When the user logs in with valid credentials
    And the user selects the "Sauce Labs Backpack" product
    And the user selects color "Blue"
    And the user sets quantity to 2
    And the user adds the product to the cart
    And the user inputs shipping address
    And the user inputs payment details
    And the user buys the "Sauce Labs Backpack" with 2 amount
    Then the user successfully bought the product
    When user sort products by "Name - Descending"
    Then user successfully sort products by "Name - Descending"
    When user sort products by "Price - Ascending"
    Then user successfully sort products by "Price - Ascending"
