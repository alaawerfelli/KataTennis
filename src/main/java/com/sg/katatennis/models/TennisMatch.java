/**
 * 
 */
package com.sg.katatennis.models;

import java.util.List;

/**
 * @author Talan.Tunisie : awerfelli
 */
public class TennisMatch {

	private TennisPlayer firstPlayer;

	private TennisPlayer secondPlayer;

	private TennisPlayer winnerPlayer;

	private List<TennisSet> matchSets;

	private String matchScore;

	/**
	 * @return the matchSets
	 */
	public List<TennisSet> getMatchSets() {
		return matchSets;
	}

	/**
	 * @param matchSets
	 *            the matchSets to set
	 */
	public void setMatchSets(List<TennisSet> matchSets) {
		this.matchSets = matchSets;
	}

	/**
	 * @return the firstPlayer
	 */
	public TennisPlayer getFirstPlayer() {
		return firstPlayer;
	}

	/**
	 * @param firstPlayer
	 *            the firstPlayer to set
	 */
	public void setFirstPlayer(TennisPlayer firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	/**
	 * @return the secondPlayer
	 */
	public TennisPlayer getSecondPlayer() {
		return secondPlayer;
	}

	/**
	 * @param secondPlayer
	 *            the secondPlayer to set
	 */
	public void setSecondPlayer(TennisPlayer secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	/**
	 * @return the winnerPlayer
	 */
	public TennisPlayer getWinnerPlayer() {
		return winnerPlayer;
	}

	/**
	 * @param winnerPlayer
	 *            the winnerPlayer to set
	 */
	public void setWinnerPlayer(TennisPlayer winnerPlayer) {
		this.winnerPlayer = winnerPlayer;
	}

	/**
	 * @return the matchScore
	 */
	public String getMatchScore() {
		return matchScore;
	}

	/**
	 * @param matchScore
	 *            the matchScore to set
	 */
	public void setMatchScore(String matchScore) {
		this.matchScore = matchScore;
	}

	/**
	 * 
	 */
	public TennisMatch() {
		super();
	}

	/**
	 * @param firstPlayer
	 * @param secondPlayer
	 * @param winnerPlayer
	 * @param matchSets
	 * @param matchScore
	 */
	public TennisMatch(TennisPlayer firstPlayer, TennisPlayer secondPlayer,
			TennisPlayer winnerPlayer, List<TennisSet> matchSets,
			String matchScore) {
		super();
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.winnerPlayer = winnerPlayer;
		this.matchSets = matchSets;
		this.matchScore = matchScore;
	}

	/**
	 * add new set to tennis match
	 * 
	 * @param tennisSet
	 */
	public void addMatchSet(TennisSet tennisSet) {
		matchSets.add(tennisSet);
	}

}
