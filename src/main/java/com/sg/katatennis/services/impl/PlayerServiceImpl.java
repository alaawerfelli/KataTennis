/**
 * 
 */
package com.sg.katatennis.services.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.katatennis.models.*;
import com.sg.katatennis.services.*;

/**
 * 
 * @author awerfelli
 */
@Service("playerService")
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	GameService gameService;

	@Autowired
	SetService setService;

	@Autowired
	MatchService matchService;

	@Override
	public TennisPlayer createNewPlayer() {
		return new TennisPlayer(RandomStringUtils.randomAlphanumeric(15),
				RandomStringUtils.randomAlphanumeric(15), 0, 0, 0);
	}

	@Override
	public void winBall(TennisPlayer player) {
		player.setGamePoint(player.getGamePoint() + 1);
	}

	@Override
	public void winBall(TennisPlayer player, TennisGame game) {
		winBall(player);
		if (game.isTieBreakGame()) {
			gameService.updateScoreOfTieBreakGame(game);
		} else {
			gameService.updateScoreOfSimpleGame(game);
		}
	}

	@Override
	public void winBall(TennisPlayer player, TennisSet set) {
		winBall(player);
		setService.updateScoreSet(set);
	}

	@Override
	public void winBall(TennisPlayer player, TennisMatch match) {
		winBall(player);
		matchService.updateMatchScore(match);
	}

}
