/**
 * 
 */
package com.sg.katatennis.services;

import com.sg.katatennis.models.TennisPlayer;
import com.sg.katatennis.models.TennisSet;

/**
 * 
 * @author awerfelli
 */
public interface SetService {

	/**
	 * create new set
	 * 
	 * @param firstPlayer
	 * @param secondPlayer
	 * @return
	 */
	TennisSet createNewSet(TennisPlayer firstPlayer, TennisPlayer secondPlayer);

	/**
	 * 
	 * update the score of set
	 * 
	 * @param tennisGame
	 * @return
	 */
	void updateScoreSet(TennisSet tennisSet);
}
