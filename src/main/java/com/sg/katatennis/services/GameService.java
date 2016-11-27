/**
 * 
 */
package com.sg.katatennis.services;

import com.sg.katatennis.models.TennisGame;
import com.sg.katatennis.models.TennisPlayer;

/**
 * @author awerfelli
 */
public interface GameService {

	/**
	 * 
	 * create new tennis game
	 * 
	 * @param firstPlayer
	 * @param secondPlayer
	 * @return
	 */
	TennisGame createNewGame(TennisPlayer firstPlayer, TennisPlayer secondPlayer);

	/**
	 * 
	 * update score of simple game
	 * 
	 * @param tennisGame
	 * @return
	 */
	void updateScoreOfSimpleGame(TennisGame tennisGame);

	/**
	 * 
	 * update score of tie-break game
	 * 
	 * @param tennisGame
	 */
	void updateScoreOfTieBreakGame(TennisGame tennisGame);

}