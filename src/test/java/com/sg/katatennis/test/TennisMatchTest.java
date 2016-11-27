/**
 * 
 */
package com.sg.katatennis.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sg.katatennis.commons.KataTennisConstants;
import com.sg.katatennis.models.TennisMatch;
import com.sg.katatennis.models.TennisPlayer;
import com.sg.katatennis.services.MatchService;
import com.sg.katatennis.services.PlayerService;

/**
 * 
 * @author awerfelli
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfigTest.class)
public class TennisMatchTest {

	TennisMatch match;

	@Autowired
	MatchService matchService;

	@Autowired
	PlayerService playerService;

	@Before
	public void initMatchTest() {
		match = matchService.createNewMatch();
	}

	@Test
	public void matchShouldStartWithNoWinner() {
		assertEquals(match.getWinnerPlayer(), null);
	}

	@Test
	public void matchShoudStartWithNullScore() {
		assertTrue(match.getMatchScore().contains(
				KataTennisConstants.START_MATCH_SCORE));
	}

	@Test
	public void winnerMatchShoudBeNotNullAfterOnePlayerReach2Sets() {
		winSet(match.getFirstPlayer());
		winSet(match.getSecondPlayer());
		winSet(match.getFirstPlayer());
		assertNotNull(match.getWinnerPlayer());
	}

	@Test
	public void matchShouldNotHasWinnerWhenNoplayersReachTwoSets() {
		winSet(match.getFirstPlayer());
		winSet(match.getSecondPlayer());
		winGame(match.getFirstPlayer());
		assertNull(match.getWinnerPlayer());
	}

	@Test
	public void playersPointsAndGamesShouldBeZeroAfterTheEndOfFirstSet() {
		winGame(match.getFirstPlayer());
		winSet(match.getSecondPlayer());
		assertThat(match.getFirstPlayer(), hasProperty("gamePoint", is(0)));
		assertThat(match.getFirstPlayer(),
				hasProperty("numberOfEarnedGame", is(0)));
		assertThat(match.getSecondPlayer(), hasProperty("gamePoint", is(0)));
		assertThat(match.getSecondPlayer(),
				hasProperty("numberOfEarnedGame", is(0)));
	}

	@Test
	public void matchMustBeEnded() {
		boolean matchNotFinished = true;
		while (matchNotFinished) {
			playerWinBallThread(match.getFirstPlayer());
			playerWinBallThread(match.getSecondPlayer());
			matchNotFinished = (match.getWinnerPlayer() == null);
		}
		assertNotNull(match.getWinnerPlayer());
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

	private void playerWinBallThread(final TennisPlayer player) {
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 5; ++i) {
					try {
						playerService.winBall(player, match);
						sleep(2500);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		t.start();
	}
}
