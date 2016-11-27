Feature: calculate set score

Scenario: one player win 6 games and has 2 won games greater than other player
Given set, first player, second player
    When first player win '6' games and second player win '4' games
    Then set finished is 'true'


