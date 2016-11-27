/**
 * 
 */
package com.sg.katatennis.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sg.katatennis.commons.KataTennisConstants;
import com.sg.katatennis.models.TennisGame;
import com.sg.katatennis.models.TennisPlayer;
import com.sg.katatennis.models.TennisSet;
import com.sg.katatennis.services.GameService;
import com.sg.katatennis.services.PlayerService;
import com.sg.katatennis.services.SetService;

/**
 * 
 * @author awerfelli
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfigTest.class)
public class TennisSetTest {

	TennisSet set;

	TennisPlayer firstPlayer;

	TennisPlayer secondPlayer;

	List<TennisGame> games;

	@Autowired
	PlayerService playerService;

	@Autowired
	GameService gameService;

	@Autowired
	SetService setService;

	@Before
	public void initSetTest() {
		firstPlayer = playerService.createNewPlayer();
		secondPlayer = playerService.createNewPlayer();
		set = setService.createNewSet(firstPlayer, secondPlayer);
	}

	@Test
	public void setShouldStartWithNoWinner() {
		assertNull(set.getWinnerPlayer());
	}

	@Test
	public void zeroShouldBeDescriptionForScore0() {
		assertEquals("0 0", set.getSetScore());
	}

	@Test
	public void setShouldHaveWinnerAfterOnlyOnePlayerReachNumberOfGamesToWinSet() {
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_GAMES_TO_WIN_SET; i++) {
			winGame(secondPlayer);
		}
		assertNotNull(set.getWinnerPlayer());
	}

	@Test
	public void playersMustBeInitializedAfterEndOfSet() {
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_GAMES_TO_WIN_SET; i++) {
			winGame(secondPlayer);
		}
		assertThat(firstPlayer, hasProperty("gamePoint", is(0)));
		assertThat(secondPlayer, hasProperty("gamePoint", is(0)));
	}

	@Test
	public void winnerMustBeFirstPlayerAfter6WonGamesAnd2GamesGreaterThanSecondPlayer() {
		for (int i = 0; i < 4; i++) {
			winGame(secondPlayer);
		}
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_GAMES_TO_WIN_SET; i++) {
			winGame(firstPlayer);
		}
		assertEquals(set.getWinnerPlayer(), firstPlayer);
	}

	@Test
	public void scoreSetShoudBeEqualsAfterSameNumberOfWonGames() {
		for (int i = 0; i < 2; i++) {
			winGame(firstPlayer);
			winGame(secondPlayer);
		}
		assertEquals(firstPlayer.getNumberOfEarnedGame(),
				secondPlayer.getNumberOfEarnedGame());
	}

	@Test
	public void currentGameMustBeTieBreakIfTwoPlayersReach6Games() {
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_GAMES_TO_WIN_SET; i++) {
			winGame(firstPlayer);
		}
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_GAMES_TO_WIN_SET; i++) {
			winGame(secondPlayer);
		}
		int setGamesSize = set.getSetGames().size();
		TennisGame currentGameOfSet = set.getSetGames().get(setGamesSize - 1);
		assertEquals(currentGameOfSet.isTieBreakGame(), true);
	}

	private void winGame(TennisPlayer player) {
		for (int i = 0; i < KataTennisConstants.NUMBER_OF_POINTS_TO_WIN_GAME; i++) {
			playerService.winBall(player, set);
		}
	}

}
