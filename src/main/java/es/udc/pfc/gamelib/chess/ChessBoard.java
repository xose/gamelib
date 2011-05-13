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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import es.udc.pfc.gamelib.board.AbstractBoard;
import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.pieces.ChessBishopMini;
import es.udc.pfc.gamelib.chess.pieces.ChessKing;
import es.udc.pfc.gamelib.chess.pieces.ChessKnight;
import es.udc.pfc.gamelib.chess.pieces.ChessPawn;
import es.udc.pfc.gamelib.chess.pieces.ChessQueen;
import es.udc.pfc.gamelib.chess.pieces.ChessRook;

/**
 * Represents a chess board
 */
public abstract class ChessBoard extends AbstractBoard<ChessBoard, ChessMovement, ChessPiece> {

	/**
	 * Gets a new board for mini-chess
	 * 
	 * @return a new mini-chess board
	 */
	public static final ChessBoard getMiniChessBoard() {
		final ChessBoard board = new ChessBoard() {
			@Override
			public final int getNumberOfColumns() {
				return 5;
			}

			@Override
			public final int getNumberOfRows() {
				return 6;
			}
		};

		board.setPieceAt(new Position(1, 1), new ChessKnight(board, ChessColor.WHITE));
		board.setPieceAt(new Position(2, 1), new ChessQueen(board, ChessColor.WHITE));
		board.setPieceAt(new Position(3, 1), new ChessKing(board, ChessColor.WHITE));
		board.setPieceAt(new Position(4, 1), new ChessBishopMini(board, ChessColor.WHITE));
		board.setPieceAt(new Position(5, 1), new ChessRook(board, ChessColor.WHITE));

		for (int col = 1; col <= board.getNumberOfColumns(); col++) {
			board.setPieceAt(new Position(col, 2), new ChessPawn(board, ChessColor.WHITE));
			board.setPieceAt(new Position(col, 5), new ChessPawn(board, ChessColor.BLACK));
		}

		board.setPieceAt(new Position(1, 6), new ChessRook(board, ChessColor.BLACK));
		board.setPieceAt(new Position(2, 6), new ChessBishopMini(board, ChessColor.BLACK));
		board.setPieceAt(new Position(3, 6), new ChessKing(board, ChessColor.BLACK));
		board.setPieceAt(new Position(4, 6), new ChessQueen(board, ChessColor.BLACK));
		board.setPieceAt(new Position(5, 6), new ChessKnight(board, ChessColor.BLACK));

		return board;
	}

	protected ChessBoard() {
	}

	@Override
	public final Set<ChessMovement> getPossibleMoves() {
		return getPossibleMoves(null);
	}

	/**
	 * Returns all possible moves for a given color
	 * 
	 * @param color
	 *            the color of the pieces to move
	 * @return an unmodifiable set with all the moves for the given color
	 */
	public final Set<ChessMovement> getPossibleMoves(final ChessColor color) {
		final HashSet<ChessMovement> moves = new HashSet<ChessMovement>();

		for (final ChessPiece piece : getAllPieces()) {
			if (color != null && piece.getColor() != color) {
				continue;
			}

			for (final Position to : piece.getAllMoves()) {
				moves.add(new ChessMovement(piece, piece.getPosition(), to, getPieceAt(to)));
			}
		}

		return Collections.unmodifiableSet(moves);
	}

	@Override
	public final ChessMovement movePiece(final Position from, final Position to) {
		final ChessPiece pieceFrom = getPieceAt(from);

		if (pieceFrom == null || !pieceFrom.canMove(to))
			return null;

		return new ChessMovement(pieceFrom, from, to, setPieceAt(to, setPieceAt(from, null)));
	}
}
