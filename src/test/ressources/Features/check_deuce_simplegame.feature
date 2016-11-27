Feature: calculate simple game score

Scenario: two players having the same number of game points
Given game, first player, second player
    When first player win '4' points and second player win '4' points
    Then game score is 'DEUCE'


