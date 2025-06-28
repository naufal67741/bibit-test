Feature: Search Investment Product in Bibit Explore

  Scenario: Logged-in user searches for investment product in Explore
    Given the user is logged into Bibit
    When the user clicks on the Explore tab
    And the user searches for Reksadana
    And user open Reksadana Saham
    Then investment search results should be displayed
