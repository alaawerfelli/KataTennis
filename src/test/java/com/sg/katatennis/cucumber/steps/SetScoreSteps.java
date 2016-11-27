/**
 * 
 */
package com.sg.katatennis.cucumber.steps;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.sg.katatennis.commons.KataTennisConstants;
import com.sg.katatennis.models.TennisPlayer;
import com.sg.katatennis.models.TennisSet;
import com.sg.katatennis.services.PlayerService;
import com.sg.katatennis.services.SetService;
import com.sg.katatennis.test.ApplicationConfigTest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * 
 * @author awerfelli
 */
@ContextConfiguration(classes = ApplicationConfigTest.class)
public class SetScoreSteps {

	@Autowired
	PlayerService playerService;

	@Autowired
	SetService setService;

	TennisSet set;

	@Given("^set, first player, second player$")
	public void setWithTwoPlayers() {
		TennisPlayer firstPlayer = playerService.createNewPlayer();
		TennisPlayer secondPlayer = playerService.createNewPlayer();
		set = setService.createNewSet(firstPlayer, secondPlayer);
	}

	@When("^first player win '(\\d+)' games and second player win '(\\d+)' games$")
	public void onePlayerGamesReachLimitToWinSetAndHas2GamesGreaterThanHisOpponent(
			final int firstPlayerGamePoints, final int secondPlayerGamePoints) {
		for (int i = 0; i < firstPlayerGamePoints; i++) {
			winGame(set.getFirstPlayer());
		}
		for (int i = 0; i < secondPlayerGamePoints; i++) {
			winGame(set.getSecondPlayer());
		}
	}

	@Then("^set finished is '(.*)'$")
	public void getScoreAfterEndOfSet(final boolean existWinnerPlayer) {
		assertEquals(set.getWinnerPlayer() != null, existWinnerPlayer);
	}

	private void winGame(TennisPlayer player) {
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_POINTS_TO_WIN_GAME; i++) {
			playerService.winBall(player, set);
		}
	}

}
