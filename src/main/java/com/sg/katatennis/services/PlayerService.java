/**
 * 
 */
package com.sg.katatennis.services;

import com.sg.katatennis.models.*;

/**
 * 
 * @author awerfelli
 */
public interface PlayerService {

	/**
	 * add new player
	 * 
	 * @return
	 */
	TennisPlayer createNewPlayer();

	/**
	 * win point and update player score
	 * 
	 * @param player
	 */
	void winBall(TennisPlayer player);

	/**
	 *
	 * win point and update game score
	 * 
	 * @param player
	 * @param game
	 */
	void winBall(TennisPlayer player, TennisGame game);

	/**
	 * win point and update set score
	 * 
	 * @param player
	 * @param set
	 */
	void winBall(TennisPlayer player, TennisSet set);

	/**
	 * win point and update match score
	 * 
	 * @param player
	 * @param match
	 */
	void winBall(TennisPlayer player, TennisMatch match);

}
