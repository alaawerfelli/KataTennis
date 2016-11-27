/**
 * 
 */
package com.sg.katatennis.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.katatennis.commons.GameScoreSequenceEnum;
import com.sg.katatennis.commons.KataTennisConstants;
import com.sg.katatennis.commons.ScoreBuilderUtility;
import com.sg.katatennis.models.TennisGame;
import com.sg.katatennis.models.TennisPlayer;
import com.sg.katatennis.models.TennisSet;
import com.sg.katatennis.services.GameService;
import com.sg.katatennis.services.PlayerService;
import com.sg.katatennis.services.SetService;

/**
 * 
 * @author awerfelli
 */
@Service("setService")
public class SetServiceImpl implements SetService {

	@Autowired
	private GameService gameService;

	@Autowired
	private PlayerService playerService;

	@Override
	public TennisSet createNewSet(TennisPlayer firstPlayer,
			TennisPlayer secondPlayer) {
		List<TennisGame> games = new ArrayList<TennisGame>();
		games.add(gameService.createNewGame(firstPlayer, secondPlayer));
		return new TennisSet(firstPlayer, secondPlayer, null, games,
				KataTennisConstants.START_SET_SCORE);
	}

	@Override
	public void updateScoreSet(TennisSet tennisSet) {
		final TennisPlayer firstPlayer = tennisSet.getFirstPlayer();
		final TennisPlayer secondPlayer = tennisSet.getSecondPlayer();
		int numberOfGamesInCurrentSet = tennisSet.getSetGames().size();
		TennisGame currentGame = tennisSet.getSetGames().get(
				numberOfGamesInCurrentSet - 1);
		// update score of game to update the score of set
		if (currentGame.isTieBreakGame()) {
			gameService.updateScoreOfTieBreakGame(currentGame);
		} else {
			gameService.updateScoreOfSimpleGame(currentGame);
		}
		TennisPlayer winnerPlayer = null;
		if (checkIfSetIsWon(tennisSet, currentGame)) {
			processEndOfSet(tennisSet, winnerPlayer);
		} else {
			String score = ScoreBuilderUtility.buildScore(
					String.valueOf(firstPlayer.getNumberOfEarnedGame()),
					String.valueOf(secondPlayer.getNumberOfEarnedGame()));
			tennisSet.setSetScore(score);
		}
		// create new game if the current game in the set is won
		if (winnerPlayer == null && currentGame.getWinnerPlayer() != null) {
			processEndOfGameInSet(tennisSet);
		}
	}

	private void processEndOfGameInSet(TennisSet tennisSet) {
		final TennisPlayer firstPlayer = tennisSet.getFirstPlayer();
		final TennisPlayer secondPlayer = tennisSet.getSecondPlayer();
		initPlayersPoints(tennisSet);
		TennisGame game = gameService.createNewGame(firstPlayer, secondPlayer);
		game.setTieBreakGame(checkIfPlayersMustStartTieBreak(firstPlayer,
				secondPlayer));
		tennisSet.addSetGame(game);
	}

	private void processEndOfSet(TennisSet tennisSet, TennisPlayer winnerPlayer) {
		final TennisPlayer firstPlayer = tennisSet.getFirstPlayer();
		final TennisPlayer secondPlayer = tennisSet.getSecondPlayer();
		String score;
		winnerPlayer = firstPlayer.getNumberOfEarnedGame() > secondPlayer
				.getNumberOfEarnedGame() ? firstPlayer : secondPlayer;
		winnerPlayer.incrementNumberOfWonSets();
		score = ScoreBuilderUtility.buildScore(winnerPlayer.getPlayerName(),
				GameScoreSequenceEnum.WON.getSequenceDescription());
		tennisSet.setSetScore(score);
		tennisSet.setWinnerPlayer(winnerPlayer);
	}

	private boolean checkIfSetIsWon(TennisSet tennisSet, TennisGame currentGame) {
		final TennisPlayer firstPlayer = tennisSet.getFirstPlayer();
		final TennisPlayer secondPlayer = tennisSet.getSecondPlayer();
		boolean isDifferenceMatchWonBetweenPlayersReached = Math
				.abs(firstPlayer.getNumberOfEarnedGame()
						- secondPlayer.getNumberOfEarnedGame()) >= KataTennisConstants.MIN_DIFF_BETWEEN_GAMES_TO_WIN_SET;
		boolean isNumberOfWonMatchReached = (firstPlayer
				.getNumberOfEarnedGame() >= KataTennisConstants.NUMBER_OF_GAMES_TO_WIN_SET)
				|| (secondPlayer.getNumberOfEarnedGame() >= KataTennisConstants.NUMBER_OF_GAMES_TO_WIN_SET);
		boolean isSetWonInTieBreak = (currentGame.isTieBreakGame() && Math
				.abs(firstPlayer.getNumberOfEarnedGame()
						- secondPlayer.getNumberOfEarnedGame()) == 1);
		return ((isDifferenceMatchWonBetweenPlayersReached && isNumberOfWonMatchReached) || isSetWonInTieBreak);
	}

	private boolean checkIfPlayersMustStartTieBreak(TennisPlayer firstPlayer,
			TennisPlayer secondPlayer) {
		if ((firstPlayer.getNumberOfEarnedGame() == KataTennisConstants.NUMBER_OF_WON_GAMES_TO_PLAY_TIE_BREAK)
				&& (secondPlayer.getNumberOfEarnedGame() == KataTennisConstants.NUMBER_OF_WON_GAMES_TO_PLAY_TIE_BREAK)) {
			return true;
		}
		return false;
	}

	private void initPlayersPoints(TennisSet set) {
		set.getFirstPlayer().setGamePoint(0);
		set.getSecondPlayer().setGamePoint(0);
	}
}
