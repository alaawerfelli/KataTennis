Feature: calculate match score

Scenario:  player reach two won sets

Given match, first player, second player
    When first player reach '2' sets before second player
    Then match finished is 'true'


