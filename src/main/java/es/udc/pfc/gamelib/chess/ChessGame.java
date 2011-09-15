/**
 * Copyright 2011 José Martínez
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.udc.pfc.gamelib.chess;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import es.udc.pfc.gamelib.GameFinishedEvent;
import es.udc.pfc.gamelib.GameStartedEvent;
import es.udc.pfc.gamelib.NewTurnEvent;
import es.udc.pfc.gamelib.PlayerAddedEvent;
import es.udc.pfc.gamelib.board.AbstractBoardGame;
import es.udc.pfc.gamelib.board.InvalidMovementException;
import es.udc.pfc.gamelib.board.PieceMovedEvent;
import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.ChessMovement.Special;

/**
 * Common methods for chess games
 */
public final class ChessGame extends AbstractBoardGame<ChessBoard, ChessMovement, ChessPlayer> {

	private final ChessRules rules;

	public ChessGame(final ChessBoard board, final ChessRules rules) {
		super(board);

		this.rules = checkNotNull(rules);
		rules.setBoard(board);
	}

	@Override
	@Nullable
	public final ChessPlayer getCurrentPlayer() {
		if (!status.equals(Status.STARTED))
			return null;

		return players.get(rules.getCurrentTurn().equals(ChessColor.WHITE) ? 0 : 1);
	}

	public final boolean addPlayer(final String name) {
		if (!status.equals(Status.WAITING_FOR_PLAYERS))
			return false;

		final ChessPlayer newPlayer = new ChessPlayer(this, name, getPlayerCount() == 1 ? ChessColor.BLACK : ChessColor.WHITE);
		if (!addPlayer(newPlayer))
			return false;

		postEvent(new PlayerAddedEvent<ChessPlayer>(newPlayer));

		if (players.size() == 2) {
			status = Status.STARTED;
			postEvent(new GameStartedEvent());
		}

		return true;
	}

	public final ChessMovement movePiece(final Position from, final Position to) throws InvalidMovementException {
		checkState(status.equals(Status.STARTED), "Game is not running");

		checkArgument(board.isValidPosition(from), "Invalid source position: %s", from.toString());
		checkArgument(board.isValidPosition(to), "Invalid destination position: %s", to.toString());

		if (!board.isPieceAt(from) || !rules.getCurrentTurn().equals(board.getPieceAt(from).getColor()))
			throw new InvalidMovementException(from, to);

		for (final ChessMovement move : getPossibleMoves()) {
			if (!from.equals(move.getFrom()) || !to.equals(move.getTo()))
				continue;

			board.setPieceAt(to, board.setPieceAt(from, null));
			movements.add(move);

			postEvent(new PieceMovedEvent<ChessMovement>(move));

			if (move.is(Special.MATE)) {
				postEvent(new GameFinishedEvent<ChessPlayer>(getCurrentPlayer()));
			} else {
				rules.nextTurn();
				postEvent(new NewTurnEvent<ChessPlayer>(getCurrentPlayer()));
			}

			return move;
		}

		throw new InvalidMovementException(from, to);
	}

	@Override
	public final ImmutableSet<ChessMovement> getPossibleMoves() {
		return rules.getPossibleMovements();
	}

	@Override
	public final String toString() {
		return "Chess game";
	}

}
