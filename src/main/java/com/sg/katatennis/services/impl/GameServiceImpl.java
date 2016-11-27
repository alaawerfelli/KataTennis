/**
 * 
 */
package com.sg.katatennis.services.impl;

import org.springframework.stereotype.Service;

import com.sg.katatennis.commons.GameScoreSequenceEnum;
import com.sg.katatennis.commons.KataTennisConstants;
import com.sg.katatennis.commons.ScoreBuilderUtility;
import com.sg.katatennis.models.TennisGame;
import com.sg.katatennis.models.TennisPlayer;
import com.sg.katatennis.services.GameService;

/**
 * 
 * @author awerfelli
 */
@Service
public class GameServiceImpl implements GameService {

	@Override
	public void updateScoreOfSimpleGame(TennisGame tennisGame) {
		final TennisPlayer firstPlayer = tennisGame.getFirstPlayer();
		final TennisPlayer secondPlayer = tennisGame.getSecondPlayer();
		TennisPlayer winnerPlayer = null;
		String score;
		if (isPlayersReachPointsLimitToWinGame(tennisGame)) {
			// players reach limit to win game with diff of 2 points
			if (Math.abs(secondPlayer.getGamePoint()
					- firstPlayer.getGamePoint()) >= KataTennisConstants.MIN_DIFF_BETWEEN_POINTS_TO_WIN_GAME) {
				processEndOfGame(tennisGame, winnerPlayer);

			}
			// players have the same score
			else if (firstPlayer.getGamePoint() == secondPlayer.getGamePoint()) {
				tennisGame.setGameScore(GameScoreSequenceEnum.DEUCE
						.getSequenceDescription());
			} else {
				// one player has the advantage
				score = ScoreBuilderUtility.buildScore(
						GameScoreSequenceEnum.ADVANTAGE
								.getSequenceDescription(),
						getWinnerPlayer(firstPlayer, secondPlayer)
								.getPlayerName());
				tennisGame.setGameScore(score);
			}
		} else {
			score = ScoreBuilderUtility
					.buildScore(GameScoreSequenceEnum.getScore(firstPlayer
							.getGamePoint()), GameScoreSequenceEnum
							.getScore(secondPlayer.getGamePoint()));
			tennisGame.setGameScore(score);
		}
	}

	@Override
	public void updateScoreOfTieBreakGame(TennisGame tennisGame) {
		final TennisPlayer firstPlayer = tennisGame.getFirstPlayer();
		final TennisPlayer secondPlayer = tennisGame.getSecondPlayer();
		TennisPlayer winnerPlayer = null;
		boolean isPointsLimitToWinTieBreakReached = (firstPlayer.getGamePoint() >= KataTennisConstants.NUMBER_OF_POINTS_TO_WIN_TIEBREAK)
				|| (secondPlayer.getGamePoint() >= KataTennisConstants.NUMBER_OF_POINTS_TO_WIN_TIEBREAK);
		boolean isDifferencePointsBetweenPlayersReached = Math.abs(firstPlayer
				.getGamePoint() - secondPlayer.getGamePoint()) >= KataTennisConstants.MIN_DIFF_BETWEEN_PLAYERS_TO_WIN_TIEBREAK_GAME;
		if (isPointsLimitToWinTieBreakReached
				&& isDifferencePointsBetweenPlayersReached) {
			processEndOfGame(tennisGame, winnerPlayer);
		} else {
			String score = ScoreBuilderUtility.buildScore(
					String.valueOf(firstPlayer.getGamePoint()),
					String.valueOf(secondPlayer.getGamePoint()));
			tennisGame.setGameScore(score);
		}
	}

	@Override
	public TennisGame createNewGame(TennisPlayer firstPlayer,
			TennisPlayer secondPlayer) {
		return new TennisGame(firstPlayer, secondPlayer, null,
				KataTennisConstants.START_GAME_SCORE);
	}

	private boolean isPlayersReachPointsLimitToWinGame(TennisGame tennisGame) {
		final TennisPlayer firstPlayer = tennisGame.getFirstPlayer();
		final TennisPlayer secondPlayer = tennisGame.getSecondPlayer();

		// two players reach the limit to win game with difference 2
		boolean isTwoPlayersGamePointsReachPointToWinGame = (firstPlayer
				.getGamePoint() >= GameScoreSequenceEnum.FORTY
				.getSequenceScore())
				&& (secondPlayer.getGamePoint() >= GameScoreSequenceEnum.FORTY
						.getSequenceScore());
		// one player reach limit to win game and other player doesn't reach the limit
		boolean existPlayerWithGamePointsGreaterThanPointsToWinGame = firstPlayer
				.getGamePoint() > GameScoreSequenceEnum.FORTY
				.getSequenceScore()
				|| secondPlayer.getGamePoint() > GameScoreSequenceEnum.FORTY
						.getSequenceScore();
		return (isTwoPlayersGamePointsReachPointToWinGame || existPlayerWithGamePointsGreaterThanPointsToWinGame);
	}

	private void processEndOfGame(TennisGame tennisGame,
			TennisPlayer winnerPlayer) {
		winnerPlayer = getWinnerPlayer(tennisGame.getFirstPlayer(),
				tennisGame.getSecondPlayer());
		tennisGame.setWinnerPlayer(winnerPlayer);
		winnerPlayer.incrementNumberOfWonGames();
		String score = ScoreBuilderUtility.buildScore(
				winnerPlayer.getPlayerName(),
				GameScoreSequenceEnum.WON.getSequenceDescription());
		tennisGame.setGameScore(score);
	}

	private TennisPlayer getWinnerPlayer(TennisPlayer firstPlayer,
			TennisPlayer secondPlayer) {
		return (firstPlayer.getGamePoint() > secondPlayer.getGamePoint()) ? firstPlayer
				: secondPlayer;
	}

}
