/**
 * 
 */
package com.sg.katatennis.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sg.katatennis.commons.KataTennisConstants;
import com.sg.katatennis.models.TennisGame;
import com.sg.katatennis.models.TennisPlayer;
import com.sg.katatennis.services.GameService;
import com.sg.katatennis.services.PlayerService;

/**
 * 
 * @author awerfelli
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfigTest.class)
public class TennisGameTest {

	TennisGame game;

	TennisPlayer firstPlayer;

	TennisPlayer secondPlayer;

	@Autowired
	PlayerService playerService;

	@Autowired
	GameService gameService;

	@Before
	public void initGameTest() {
		firstPlayer = new TennisPlayer("ID1", "Jean", 0);
		secondPlayer = new TennisPlayer("ID2", "Eric", 0);
		game = new TennisGame(firstPlayer, secondPlayer, null);
	}

	@Test
	public void gameShouldStartWithNoWinner() {
		assertNull(game.getWinnerPlayer());
	}

	@Test
	public void loveShouldBeDescriptionForScore0() {
		gameService.updateScoreOfSimpleGame(game);
		assertThat(game, hasProperty("gameScore", is("0 0")));
	}

	@Test
	public void fifteenShouldBeDescriptionForScore1() {
		playerService.winBall(firstPlayer, game);
		assertThat(game, hasProperty("gameScore", is("15 0")));
	}

	@Test
	public void thirtyShouldBeDescriptionForScore2() {
		for (int i = 0; i < 2; i++) {
			playerService.winBall(firstPlayer, game);
		}
		assertThat(game, hasProperty("gameScore", is("30 0")));
	}

	@Test
	public void fortyShouldBeDescriptionForScore3() {
		for (int i = 0; i < 3; i++) {
			playerService.winBall(firstPlayer, game);
		}
		assertThat(game, hasProperty("gameScore", is("40 0")));
	}

	@Test
	public void advantageShouldBeDescriptionWhenOnePlayerMarkThreePointsAndHasOnePointMoreThanHisOpponent() {
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_POINTS_TO_WIN_GAME; i++) {
			playerService.winBall(firstPlayer, game);
		}
		for (int i = 0; i < 3; i++) {
			playerService.winBall(secondPlayer, game);
		}
		assertThat(game, hasProperty("gameScore", is("ADVANTAGE Jean")));
	}

	@Test
	public void deuceShouldBeDescriptionWhenTwoPlayersMarkAtLeastThreePointsAndTheirPointsAreEqual() {
		{
			for (int i = 0; i < KataTennisConstants.NUMBER_OF_POINTS_TO_WIN_GAME; i++) {
				playerService.winBall(firstPlayer, game);
				playerService.winBall(secondPlayer, game);
			}
			assertThat(game, hasProperty("gameScore", is("DEUCE")));
			playerService.winBall(firstPlayer, game);
			assertThat(game, hasProperty("gameScore", not("DEUCE")));
		}
	}

	@Test
	public void gameShouldBeWonByTheFirstPlayerAfterFourWonPoints() {
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_POINTS_TO_WIN_GAME; i++) {
			playerService.winBall(firstPlayer, game);
		}
		assertThat(game, hasProperty("gameScore", is(not("Jean won"))));
	}

	@Test
	public void tieBreakGameShoudHaveWinner() {
		game.setTieBreakGame(true);
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_POINTS_TO_WIN_TIEBREAK; i++) {
			playerService.winBall(firstPlayer, game);
		}
		assertNotNull(game.getWinnerPlayer());
	}

}
