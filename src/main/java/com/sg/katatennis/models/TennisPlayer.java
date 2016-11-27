/**
 * 
 */
package com.sg.katatennis.models;

/**
 * 
 * @author awerfelli
 */
public class TennisPlayer {

	private String playerId;

	private String playerName;

	private int gamePoint;

	private int numberOfEarnedGame;

	private int numberOfEarnedSet;

	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName
	 *            the playerName to set
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * @return the gamePoint
	 */
	public int getGamePoint() {
		return gamePoint;
	}

	/**
	 * @param gamePoint
	 *            the gamePoint to set
	 */
	public void setGamePoint(int gamePoint) {
		this.gamePoint = gamePoint;
	}

	/**
	 * @return the numberOfEarnedGame
	 */
	public int getNumberOfEarnedGame() {
		return numberOfEarnedGame;
	}

	/**
	 * @param numberOfEarnedGame
	 *            the numberOfEarnedGame to set
	 */
	public void setNumberOfEarnedGame(int numberOfEarnedGame) {
		this.numberOfEarnedGame = numberOfEarnedGame;
	}

	/**
	 * @return the numberOfEarnedSet
	 */
	public int getNumberOfEarnedSet() {
		return numberOfEarnedSet;
	}

	/**
	 * @param numberOfEarnedSet
	 *            the numberOfEarnedSet to set
	 */
	public void setNumberOfEarnedSet(int numberOfEarnedSet) {
		this.numberOfEarnedSet = numberOfEarnedSet;
	}

	/**
	 * @param playerId
	 * @param playerName
	 * @param gamePoint
	 */
	public TennisPlayer(String playerId, String playerName, int gamePoint) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.gamePoint = gamePoint;
	}

	/**
	 * @param playerId
	 * @param playerName
	 * @param gamePoint
	 * @param numberOfEarnedGame
	 * @param numberOfEarnedSet
	 */
	public TennisPlayer(String playerId, String playerName, int gamePoint,
			int numberOfEarnedGame, int numberOfEarnedSet) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.gamePoint = gamePoint;
		this.numberOfEarnedGame = numberOfEarnedGame;
		this.numberOfEarnedSet = numberOfEarnedSet;
	}

	public void incrementNumberOfWonPoints() {
		this.gamePoint++;
	}

	public void incrementNumberOfWonGames() {
		this.numberOfEarnedGame++;
	}

	public void incrementNumberOfWonSets() {
		this.numberOfEarnedSet++;
	}

}
