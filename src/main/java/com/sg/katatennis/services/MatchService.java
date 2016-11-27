/**
 * 
 */
package com.sg.katatennis.services;

import com.sg.katatennis.models.TennisMatch;

/**
 * @author awerfelli
 */
public interface MatchService {

	/**
	 * create new tennis match
	 * 
	 * @return
	 */
	TennisMatch createNewMatch();

	/**
	 * update the score of match
	 * 
	 * @param match
	 */
	void updateMatchScore(TennisMatch match);

}
