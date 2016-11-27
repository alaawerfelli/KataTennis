Feature: calculate tie-break game score

Scenario: one player reach the limit of games to win tie-break game

Given tie-break game, first player, second player
    When first player win '7' points and second player has '2' points 
    Then tie-break game finished is 'true'
