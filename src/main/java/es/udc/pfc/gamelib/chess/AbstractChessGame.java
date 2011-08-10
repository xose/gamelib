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

/**
 * Common methods for chess games
 */
public abstract class AbstractChessGame extends AbstractBoardGame<ChessBoard, ChessMovement, ChessPlayer> implements ChessGame {
	
	protected AbstractChessGame(final ChessBoard board) {
		super(board);
	}
	
	/**
	 * Returns the color for the current turn
	 * 
	 * @return the color for the current turn
	 */
	protected final ChessColor getCurrentTurn() {
		if (!status.equals(Status.STARTED))
			return null;
		
		return movements.size() % 2 == 0 ? ChessColor.WHITE : ChessColor.BLACK;
	}
	
	@Override @Nullable public final ChessPlayer getCurrentPlayer() {
		if (!status.equals(Status.STARTED))
			return null;
		
		return players.get(getCurrentTurn() == ChessColor.WHITE ? 0 : 1);
	}
	
	@Override public final boolean addPlayer(final String name) {
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
	
	@Override public final ImmutableSet<ChessMovement> getPossibleMoves() {
		return getPossibleMoves(null);
	}
	
	/**
	 * Returns all possible moves for a given color
	 * 
	 * @param color
	 *            the color of the pieces to move
	 * @return an unmodifiable set with all the moves for the given color
	 */
	public abstract ImmutableSet<ChessMovement> getPossibleMoves(@Nullable final ChessColor color);
	
	@Override public final void movePiece(final Position from, final Position to) throws InvalidMovementException {
		checkState(status.equals(Status.STARTED), "Game is not running");
		
		checkArgument(board.isValidPosition(from), "Invalid source position: %s", from.toString());
		checkArgument(board.isValidPosition(to), "Invalid destination position: %s", to.toString());
		
		if (!board.isPieceAt(from) || !getCurrentTurn().equals(board.getPieceAt(from).getColor()))
			throw new InvalidMovementException(from, to);
		
		for (final ChessMovement move : getPossibleMoves()) {
			if (from.equals(move.getFrom()) && to.equals(move.getTo())) {
				board.setPieceAt(to, board.setPieceAt(from, null));
				movements.add(move);
				
				postEvent(new PieceMovedEvent<ChessMovement>(move));
				
				if (!move.isMate()) {
					postEvent(new NewTurnEvent<ChessPlayer>(getCurrentPlayer()));
				} else {
					postEvent(new GameFinishedEvent<ChessPlayer>(getCurrentPlayer()));
				}
				
				return;
			}
		}
		
		throw new InvalidMovementException(from, to);
	}
	
	@Override public abstract String toString();
	
}
