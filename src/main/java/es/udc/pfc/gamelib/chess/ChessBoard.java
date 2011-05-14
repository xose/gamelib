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

/**
 * Represents a chess board
 */
public abstract class ChessBoard extends AbstractBoard<ChessBoard, ChessMovement, ChessPiece> {

	public static final String CHESSBOARD_MINI = "rbkqn/ppppp/5/5/PPPPP/NQKBR";

	protected ChessBoard() {
	}

	public static final ChessBoard fromString(final String input) {
		final String[] lines = input.split("/");

		// Get column size on first row
		int colsize = 0;
		for (final char c : lines[0].toCharArray()) {
			colsize += Character.isDigit(c) ? Integer.parseInt(Character.toString(c)) : 1;
		}

		final int rows = lines.length;
		final int cols = colsize;
		final ChessBoard board = new ChessBoard() {
			@Override
			public final int getNumberOfRows() {
				return rows;
			}

			@Override
			public final int getNumberOfColumns() {
				return cols;
			}
		};

		for (int currow = 1; currow <= rows; currow++) {
			int curcol = 1;
			for (final char c : lines[lines.length - currow].toCharArray()) {
				if (Character.isDigit(c)) {
					curcol += Integer.parseInt(Character.toString(c));
					continue;
				}

				board.setPieceAt(new Position(curcol, currow), ChessPiece.fromString(board, c));
				curcol++;
			}

			if (curcol - 1 != colsize)
				throw new IllegalArgumentException("Expected " + colsize + " columns on row " + currow + ", got " + (curcol - 1));
		}

		return board;
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
