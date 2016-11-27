package com.sg.katatennis.cucumber.steps;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.sg.katatennis.commons.KataTennisConstants;
import com.sg.katatennis.models.TennisMatch;
import com.sg.katatennis.models.TennisPlayer;
import com.sg.katatennis.services.MatchService;
import com.sg.katatennis.services.PlayerService;
import com.sg.katatennis.test.ApplicationConfigTest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * 
 * @author awerfelli
 */
@ContextConfiguration(classes = ApplicationConfigTest.class)
public class MatchScoreSteps {

	@Autowired
	PlayerService playerService;

	@Autowired
	MatchService matchService;

	TennisMatch match;

	@Given("^match, first player, second player$")
	public void matchWithTwoPlayers() {
		match = matchService.createNewMatch();
	}

	@When("^first player reach '(\\d+)' sets before second player$")
	public void playerOneWinTwoSetBeforeSecondPlayer(
			final int firstPlayerSetNumber) {
		for (int i = 0; i < firstPlayerSetNumber; i++) {
			winSet(match.getFirstPlayer());
		}
	}

	@Then("^match finished is '(.*)'$")
	public void checkEndOfMatch(final boolean existWinnerPlayer) {
		assertEquals(match.getWinnerPlayer() != null, existWinnerPlayer);
	}

	private void winSet(TennisPlayer player) {
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_GAMES_TO_WIN_SET; i++) {
			winGame(player);
		}
	}

	private void winGame(TennisPlayer player) {
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_POINTS_TO_WIN_GAME; i++) {
			playerService.winBall(player, match);
		}
	}

}
