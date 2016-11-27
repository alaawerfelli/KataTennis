/**
 * 
 */
package com.sg.katatennis.commons;

/**
 * 
 * @author awerfelli
 */
public enum GameScoreSequenceEnum {

	LOVE("0", 0), FIFTEEN("15", 1), THIRTY("30", 2), FORTY("40", 3), DEUCE(
			"DEUCE"), ADVANTAGE("ADVANTAGE"), WON("WON");

	private String sequenceDescription;

	private int sequenceScore;

	/**
	 * @return the sequenceDescription
	 */
	public String getSequenceDescription() {
		return sequenceDescription;
	}

	/**
	 * @param sequenceDescription
	 *            the sequenceDescription to set
	 */
	public void setSequenceDescription(String sequenceDescription) {
		this.sequenceDescription = sequenceDescription;
	}

	/**
	 * @return the sequenceScore
	 */
	public int getSequenceScore() {
		return sequenceScore;
	}

	/**
	 * @param sequenceScore
	 *            the sequenceScore to set
	 */
	public void setSequenceScore(int sequenceScore) {
		this.sequenceScore = sequenceScore;
	}

	private GameScoreSequenceEnum(String sequenceDescription, int sequenceScore) {
		this.sequenceDescription = sequenceDescription;
		this.sequenceScore = sequenceScore;
	}

	private GameScoreSequenceEnum(String sequenceDescription) {
		this.sequenceDescription = sequenceDescription;
	}

	/**
	 * 
	 * get score description
	 * 
	 * @param val
	 * @return
	 */
	public static String getScore(int val) {
		String score = null;
		switch (val) {
		case 0:
			score = LOVE.getSequenceDescription();
			break;
		case 1:
			score = FIFTEEN.getSequenceDescription();
			break;
		case 2:
			score = THIRTY.getSequenceDescription();
			break;
		case 3:
			score = FORTY.getSequenceDescription();
			break;
		default:
			break;
		}
		return score;
	}

}
