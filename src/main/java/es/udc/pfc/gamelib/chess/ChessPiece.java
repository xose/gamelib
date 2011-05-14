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

import es.udc.pfc.gamelib.board.AbstractPiece;
import es.udc.pfc.gamelib.chess.pieces.ChessBishopMini;
import es.udc.pfc.gamelib.chess.pieces.ChessKing;
import es.udc.pfc.gamelib.chess.pieces.ChessKnight;
import es.udc.pfc.gamelib.chess.pieces.ChessPawn;
import es.udc.pfc.gamelib.chess.pieces.ChessQueen;
import es.udc.pfc.gamelib.chess.pieces.ChessRook;

/**
 * Represents a chess piece
 */
public abstract class ChessPiece extends AbstractPiece<ChessBoard, ChessMovement, ChessPiece> {

	private final ChessColor color;

	protected ChessPiece(final ChessBoard board, final ChessColor color) {
		super(board);
		this.color = color;
	}

	/**
	 * Returns the color of this piece
	 * 
	 * @return the color of this piece
	 */
	public final ChessColor getColor() {
		return color;
	}

	@Override
	public final boolean isEnemy(final ChessPiece other) {
		return color != other.getColor();
	}

	public static final ChessPiece fromString(final ChessBoard board, final char input) {
		final ChessColor color = Character.isUpperCase(input) ? ChessColor.WHITE : ChessColor.BLACK;

		final char piece = Character.toUpperCase(input);
		if (piece == 'K')
			return new ChessKing(board, color);
		if (piece == 'Q')
			return new ChessQueen(board, color);
		if (piece == 'B')
			return new ChessBishopMini(board, color);
		if (piece == 'N')
			return new ChessKnight(board, color);
		if (piece == 'R')
			return new ChessRook(board, color);
		if (piece == 'P')
			return new ChessPawn(board, color);

		throw new IllegalArgumentException("Unknown chess piece " + input);
	}

}
