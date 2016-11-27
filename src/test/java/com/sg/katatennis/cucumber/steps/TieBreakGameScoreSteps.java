/**
 * 
 */
package com.sg.katatennis.cucumber.steps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.sg.katatennis.models.TennisGame;
import com.sg.katatennis.models.TennisPlayer;
import com.sg.katatennis.services.GameService;
import com.sg.katatennis.services.PlayerService;
import com.sg.katatennis.test.ApplicationConfigTest;
import static org.junit.Assert.assertEquals;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * 
 * @author awerfelli
 */
@ContextConfiguration(classes = ApplicationConfigTest.class)
public class TieBreakGameScoreSteps {

	@Autowired
	PlayerService playerService;

	@Autowired
	GameService gameService;

	TennisGame game;

	@Given("^tie-break game, first player, second player$")
	public void tieBreakGameWithTwoPlayers() {
		TennisPlayer firstPlayer = playerService.createNewPlayer();
		TennisPlayer secondPlayer = playerService.createNewPlayer();
		game = gameService.createNewGame(firstPlayer, secondPlayer);
		game.setTieBreakGame(true);
	}

	@When("^first player win '(\\d+)' points and second player has '(\\d+)' points$")
	public void OneplayerPointsReachLimitToWinTieBreakGameAndHas2PointsGreaterThanHisOpponent(final int firstPlayerPoints,
			final int secondPlayerPoints) {

		for (int i = 0; i < firstPlayerPoints; i++) {
			playerService.winBall(game.getFirstPlayer(), game);
		}

		for (int i = 0; i < secondPlayerPoints; i++) {
			playerService.winBall(game.getSecondPlayer(), game);
		}
	}

	@Then("^tie-break game finished is '(.*)'$")
	public void tieBreakGameFinished(final boolean existWinnerPlayer) {
		assertEquals(game.getWinnerPlayer() != null, existWinnerPlayer);
	}

}
