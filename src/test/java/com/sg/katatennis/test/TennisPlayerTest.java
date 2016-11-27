/**
 * 
 */
package com.sg.katatennis.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sg.katatennis.models.TennisPlayer;
import com.sg.katatennis.services.PlayerService;

/**
 * 
 * @author awerfelli
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfigTest.class)
public class TennisPlayerTest {

	@Autowired
	PlayerService playerService;

	TennisPlayer firstPlayer;

	TennisPlayer secondPlayer;

	@Before
	public void initPlayerTest() {
		firstPlayer = new TennisPlayer("ID1", "Michel", 0);
		secondPlayer = new TennisPlayer("ID2", "Jean", 0);
	}

	@Test
	public void checkCreatedPlayersNotNull() {
		firstPlayer = playerService.createNewPlayer();
		secondPlayer = playerService.createNewPlayer();
		assertNotNull(firstPlayer);
		assertNotNull(firstPlayer);
	}

	@Test
	public void pointsCanBeAddedToPlayer() {
		playerService.winBall(firstPlayer);
		assertEquals(1, firstPlayer.getGamePoint());
	}

	@Test
	public void pointsMustBeIncrmentedByOneWhenPlayerWinBall() {
		playerService.winBall(firstPlayer);
		assertEquals(1, firstPlayer.getGamePoint());
	}

}
