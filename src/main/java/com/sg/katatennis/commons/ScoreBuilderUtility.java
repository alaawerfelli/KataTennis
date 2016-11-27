/**
 * 
 */
package com.sg.katatennis.commons;

/**
 * 
 * @author awerfelli
 */
public final class ScoreBuilderUtility {

	/**
	 * 
	 * build score description
	 * 
	 * @param scoreFirstPart
	 * @param scoreSecondPart
	 * @return
	 */
	public static final String buildScore(String scoreFirstPart,
			String scoreSecondPart) {
		StringBuilder scoreBuilder = new StringBuilder(scoreFirstPart);
		scoreBuilder.append(" ").append(scoreSecondPart);
		return scoreBuilder.toString();
	}

}
