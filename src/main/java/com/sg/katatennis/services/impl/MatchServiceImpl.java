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
import com.sg.katatennis.models.TennisMatch;
import com.sg.katatennis.models.TennisPlayer;
import com.sg.katatennis.models.TennisSet;
import com.sg.katatennis.services.MatchService;
import com.sg.katatennis.services.PlayerService;
import com.sg.katatennis.services.SetService;

/**
 * 
 * @author awerfelli
 */
@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	PlayerService playerService;

	@Autowired
	SetService setService;

	@Override
	public void updateMatchScore(TennisMatch match) {
		final int numberOfSetInCurrentMatch = match.getMatchSets().size();
		TennisSet currentSet = match.getMatchSets().get(
				numberOfSetInCurrentMatch - 1);
		TennisPlayer winnerPlayer = null;
		// update score of set to update the score of match
		setService.updateScoreSet(currentSet);
		if (isMatchWon(match)) {
			processEndOfMatch(match, winnerPlayer);
		} else {
			match.setMatchScore(buildMatchScore(match, currentSet));
		}
		// process the end of current set 
		if (currentSet.getWinnerPlayer() != null) {
			processEndOfSetInMatch(match);
		}
	}

	@Override
	public TennisMatch createNewMatch() {
		TennisPlayer firstPlayer = playerService.createNewPlayer();
		TennisPlayer secondPlayer = playerService.createNewPlayer();
		List<TennisSet> matchSet = new ArrayList<TennisSet>();
		TennisSet firstMatchSet = setService.createNewSet(firstPlayer,
				secondPlayer);
		matchSet.add(firstMatchSet);
		TennisMatch match = new TennisMatch(firstPlayer, secondPlayer, null,
				matchSet, buildInitialScoreOfMatch(firstPlayer, secondPlayer));
		if (!isMatchHas2DifferentPlayers(match)) {
			throw new RuntimeException(
					KataTennisConstants.MATCH_PLAYERS_CONFIGURATION_PROBLEM_MSG);
		}
		return match;
	}

	private void processEndOfSetInMatch(TennisMatch match) {
		// initialize points and games of each player and add new set to match
		initPlayersPointsAndGames(match);
		TennisSet set = setService.createNewSet(match.getFirstPlayer(),
				match.getSecondPlayer());
		match.addMatchSet(set);
	}

	private void processEndOfMatch(TennisMatch match, TennisPlayer winnerPlayer) {
		final TennisPlayer firstPlayer = match.getFirstPlayer();
		final TennisPlayer secondPlayer = match.getSecondPlayer();
		String score;
		winnerPlayer = firstPlayer.getNumberOfEarnedSet() > secondPlayer
				.getNumberOfEarnedSet() ? firstPlayer : secondPlayer;
		match.setWinnerPlayer(winnerPlayer);
		score = ScoreBuilderUtility.buildScore(winnerPlayer.getPlayerName(),
				GameScoreSequenceEnum.WON.getSequenceDescription());
		match.setMatchScore(score);
	}

	private String buildMatchScore(TennisMatch match, TennisSet currentMatchSet) {
		final List<TennisGame> currentSetGames = currentMatchSet.getSetGames();
		final TennisGame currentGameOfSet = currentSetGames.get(currentSetGames
				.size() - 1);
		StringBuilder builder = new StringBuilder();
		builder.append(match.getFirstPlayer().getPlayerName());
		builder.append(" VS ");
		builder.append(match.getSecondPlayer().getPlayerName()).append("\n");
		builder.append(" Match [ "
				+ match.getFirstPlayer().getNumberOfEarnedSet() + " "
				+ match.getSecondPlayer().getNumberOfEarnedSet() + " ],");
		builder.append("Set [ ").append(currentMatchSet.getSetScore())
				.append(" ],");
		builder.append("Game [").append(currentGameOfSet.getGameScore())
				.append(" ]");
		return builder.toString();
	}

	private boolean isMatchWon(TennisMatch match) {
		final TennisPlayer firstPlayer = match.getFirstPlayer();
		final TennisPlayer secondPlayer = match.getSecondPlayer();
		boolean numberOfWonSetReached = (firstPlayer.getNumberOfEarnedSet() == KataTennisConstants.NUMBER_OF_SETS_TO_WIN_MATCH)
				|| (secondPlayer.getNumberOfEarnedSet() == KataTennisConstants.NUMBER_OF_SETS_TO_WIN_MATCH);
		return numberOfWonSetReached;
	}

	private boolean isMatchHas2DifferentPlayers(TennisMatch match) {
		final TennisPlayer firstPlayer = match.getFirstPlayer();
		final TennisPlayer secondPlayer = match.getSecondPlayer();
		if (firstPlayer != null
				&& secondPlayer != null
				&& !firstPlayer.getPlayerId()
						.equals(secondPlayer.getPlayerId())) {
			return true;
		}
		return false;
	}

	private void initPlayersPointsAndGames(TennisMatch match) {
		match.getFirstPlayer().setGamePoint(0);
		match.getSecondPlayer().setGamePoint(0);
		match.getFirstPlayer().setNumberOfEarnedGame(0);
		match.getSecondPlayer().setNumberOfEarnedGame(0);
	}

	private String buildInitialScoreOfMatch(TennisPlayer firstPlayer,
			TennisPlayer secondPlayer) {
		StringBuilder startMatchScoreBuilder = new StringBuilder();
		startMatchScoreBuilder.append(firstPlayer.getPlayerName());
		startMatchScoreBuilder.append(" VS ");
		startMatchScoreBuilder.append(secondPlayer.getPlayerName())
				.append("\n");
		startMatchScoreBuilder.append(KataTennisConstants.START_MATCH_SCORE);
		return startMatchScoreBuilder.toString();
	}

}
