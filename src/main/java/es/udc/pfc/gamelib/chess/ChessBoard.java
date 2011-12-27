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

import static com.google.common.base.Preconditions.checkNotNull;

import es.udc.pfc.gamelib.board.AbstractBoard;
import es.udc.pfc.gamelib.board.Position;

/**
 * Represents a chess board.
 */
public final class ChessBoard extends AbstractBoard<ChessPiece> {

	public static final String CHESSBOARD_STANDARD = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	public static final String CHESSBOARD_MINI = "rbkqn/ppppp/5/5/PPPPP/NQKBR";

	private ChessBoard(final int rows, final int cols) {
		super(rows, cols);
	}

	/**
	 * Parses a board from the text representation.
	 * 
	 * @param input the text representation of the board
	 * @return a new board
	 */
	public static final ChessBoard fromString(final String input) {
		final String[] lines = checkNotNull(input).split("/");

		// Get column size on first row
		int colsize = 0;
		for (final char c : lines[0].toCharArray()) {
			colsize += Character.isDigit(c) ? Integer.parseInt(Character.toString(c)) : 1;
		}

		final ChessBoard board = new ChessBoard(lines.length, colsize);

		for (int currow = 1; currow <= lines.length; currow++) {
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
	
}
