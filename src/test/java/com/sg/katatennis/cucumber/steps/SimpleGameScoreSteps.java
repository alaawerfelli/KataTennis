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
public class SimpleGameScoreSteps {

	@Autowired
	PlayerService playerService;

	@Autowired
	GameService gameService;

	TennisGame game;

	@Given("^game, first player, second player$")
	public void gameWithTwoPlayers() {
		TennisPlayer firstPlayer = playerService.createNewPlayer();
		TennisPlayer secondPlayer = playerService.createNewPlayer();
		game = gameService.createNewGame(firstPlayer, secondPlayer);
	}

	@When("^first player win '(\\d+)' points and second player win '(\\d+)' points$")
	public void playersWinBall(final int firstPlayerPoints,
			final int secondPlayerPoints) {
		for (int i = 0; i < firstPlayerPoints; i++) {
			playerService.winBall(game.getFirstPlayer(), game);
		}
		for (int i = 0; i < secondPlayerPoints; i++) {
			playerService.winBall(game.getSecondPlayer(), game);
		}
	}

	@Then("^game score is '(.*)'$")
	public void getScoreGameAfterPlayedBall(final String scoreGame) {
		assertEquals(game.getGameScore(), scoreGame);
	}

}
