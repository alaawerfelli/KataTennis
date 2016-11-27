/**
 * 
 */
package com.sg.katatennis.models;

import java.util.List;

/**
 * 
 * @author awerfelli
 */
public class TennisSet {

	private TennisPlayer firstPlayer;

	private TennisPlayer secondPlayer;

	private TennisPlayer winnerPlayer;

	private List<TennisGame> setGames;

	private String setScore;

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
	 * @return the setGames
	 */
	public List<TennisGame> getSetGames() {
		return setGames;
	}

	/**
	 * @param setGames
	 *            the setGames to set
	 */
	public void setSetGames(List<TennisGame> setGames) {
		this.setGames = setGames;
	}

	/**
	 * add new game to tennis set
	 * 
	 * @param tennisSet
	 */
	public void addSetGame(TennisGame tennisGame) {
		setGames.add(tennisGame);
	}

	/**
	 * @return the setScore
	 */
	public String getSetScore() {
		return setScore;
	}

	/**
	 * @param setScore
	 *            the setScore to set
	 */
	public void setSetScore(String setScore) {
		this.setScore = setScore;
	}

	/**
	 * 
	 */
	public TennisSet() {
		super();
	}

	/**
	 * @param firstPlayer
	 * @param secondPlayer
	 * @param winnerPlayer
	 * @param setGames
	 */
	public TennisSet(TennisPlayer firstPlayer, TennisPlayer secondPlayer,
			TennisPlayer winnerPlayer, List<TennisGame> setGames) {
		super();
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.winnerPlayer = winnerPlayer;
		this.setGames = setGames;
	}

	/**
	 * @param firstPlayer
	 * @param secondPlayer
	 * @param winnerPlayer
	 * @param setGames
	 * @param setScore
	 */
	public TennisSet(TennisPlayer firstPlayer, TennisPlayer secondPlayer,
			TennisPlayer winnerPlayer, List<TennisGame> setGames,
			String setScore) {
		super();
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.winnerPlayer = winnerPlayer;
		this.setGames = setGames;
		this.setScore = setScore;
	}
	
}
