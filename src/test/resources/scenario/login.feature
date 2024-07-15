Feature: Bot for site Klavagonki

  Background: I am on the main page
    Given I am on site "https://klavogonki.ru/go?type=normal"

  Scenario: Bot runs game and inputs words
    When We start game
    And We wait start of the game
    And Input highlighted words
    Then We record that the game is over and there are more than 1500 symbols