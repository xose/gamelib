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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.udc.pfc.gamelib.AbstractGame;
import es.udc.pfc.gamelib.board.Position;

/**
 * Represents a chess game
 */
public final class ChessGame extends AbstractGame<ChessGame, ChessPlayer> {

	private final ChessBoard chessBoard;
	private final List<ChessMovement> movements;

	public enum ChessType {
		MiniChess
	}

	/**
	 * Constructs a new chess game
	 */
	public ChessGame(final ChessType type) {
		movements = new ArrayList<ChessMovement>();

		switch (type) {
		case MiniChess:
			chessBoard = ChessBoard.getMiniChessBoard();
			break;
		default:
			throw new IllegalArgumentException("Unknown chess type");
		}
	}

	@Override
	public final int getMinPlayerCount() {
		return 2;
	}

	@Override
	public final int getMaxPlayerCount() {
		return 2;
	}

	/**
	 * Returns the color for the current turn
	 * 
	 * @return the color for the current turn
	 */
	public final ChessColor getCurrentTurn() {
		if (getPlayerCount() != 2)
			return null;

		return movements.size() % 2 == 0 ? ChessColor.WHITE : ChessColor.BLACK;
	}

	@Override
	public final ChessPlayer getCurrentPlayer() {
		if (getPlayerCount() != 2)
			return null;

		return getPlayers().get(getCurrentTurn() == ChessColor.WHITE ? 0 : 1);
	}

	public final boolean addPlayer(final String name) {
		return addPlayer(new ChessPlayer(this, name, (getPlayerCount() == 1 ? ChessColor.BLACK : ChessColor.WHITE)));
	}

	/**
	 * Returns the board for this chess game
	 * 
	 * @return the board for this game
	 */
	public final ChessBoard getBoard() {
		return chessBoard;
	}

	/**
	 * Returns the list of movements made in this game
	 * 
	 * @return an unmodifiable list of the movements in this game
	 */
	public final List<ChessMovement> getMovements() {
		return Collections.unmodifiableList(movements);
	}

	/**
	 * Moves a chess piece
	 * 
	 * @param from
	 *            the source of the movement
	 * @param to
	 *            the destination of the movement
	 * @return true if the movement succeeded, false otherwise
	 */
	public final boolean move(final Position from, final Position to) {
		if (chessBoard.getPieceAt(from) == null || chessBoard.getPieceAt(from).getColor() != getCurrentTurn())
			return false;

		final ChessMovement movement = chessBoard.movePiece(from, to);
		if (movement == null)
			return false;

		movements.add(movement);

		return true;
	}

	/**
	 * Undoes the last movement
	 * 
	 * @return true if the movement was undone, false otherwise
	 */
	public final boolean undo() {
		if (movements.isEmpty())
			return false;

		final ChessMovement lastMove = movements.get(movements.size() - 1);

		chessBoard.setPieceAt(lastMove.getFrom(), lastMove.getPiece());
		chessBoard.setPieceAt(lastMove.getTo(), lastMove.getAttackedPiece());

		movements.remove(lastMove);

		return true;
	}

	@Override
	public String toString() {
		return String.format("Chess Game");
	}
}
