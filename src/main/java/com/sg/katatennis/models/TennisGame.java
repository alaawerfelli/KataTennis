/**
 * 
 */
package com.sg.katatennis.models;

/**
 * 
 * @author awerfelli
 */
public class TennisGame {

	private TennisPlayer firstPlayer;

	private TennisPlayer secondPlayer;

	private TennisPlayer winnerPlayer;

	private String gameScore;

	private boolean isTieBreakGame;

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
	 * @return the gameScore
	 */
	public String getGameScore() {
		return gameScore;
	}

	/**
	 * @param gameScore
	 *            the gameScore to set
	 */
	public void setGameScore(String gameScore) {
		this.gameScore = gameScore;
	}

	/**
	 * @return the isTieBreakGame
	 */
	public boolean isTieBreakGame() {
		return isTieBreakGame;
	}

	/**
	 * @param isTieBreakGame
	 *            the isTieBreakGame to set
	 */
	public void setTieBreakGame(boolean isTieBreakGame) {
		this.isTieBreakGame = isTieBreakGame;
	}

	/**
	 * 
	 */
	public TennisGame() {
		super();
	}

	/**
	 * @param firstPlayer
	 * @param secondPlayer
	 * @param winnerPlayer
	 */
	public TennisGame(TennisPlayer firstPlayer, TennisPlayer secondPlayer,
			TennisPlayer winnerPlayer) {
		super();
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.winnerPlayer = winnerPlayer;
	}

	/**
	 * @param firstPlayer
	 * @param secondPlayer
	 * @param winnerPlayer
	 * @param gameScore
	 */
	public TennisGame(TennisPlayer firstPlayer, TennisPlayer secondPlayer,
			TennisPlayer winnerPlayer, String gameScore) {
		super();
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.winnerPlayer = winnerPlayer;
		this.gameScore = gameScore;
	}

}
