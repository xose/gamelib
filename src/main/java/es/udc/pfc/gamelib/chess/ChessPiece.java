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

import com.google.common.base.Ascii;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

import es.udc.pfc.gamelib.board.AbstractPiece;
import es.udc.pfc.gamelib.board.Piece;
import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.pieces.ChessBishop;
import es.udc.pfc.gamelib.chess.pieces.ChessKing;
import es.udc.pfc.gamelib.chess.pieces.ChessKnight;
import es.udc.pfc.gamelib.chess.pieces.ChessPawn;
import es.udc.pfc.gamelib.chess.pieces.ChessQueen;
import es.udc.pfc.gamelib.chess.pieces.ChessRook;

/**
 * Represents a chess piece.
 */
public abstract class ChessPiece extends AbstractPiece {
	
	private final ChessColor color;
	private final char notation;
	
	protected ChessPiece(final ChessBoard board, final ChessColor color, final char notation) {
		super(board);
		
		this.color = checkNotNull(color);
		this.notation = notation;
		
		checkArgument(Ascii.isUpperCase(notation));
	}
	
	/**
	 * Returns the color of this piece.
	 * 
	 * @return the color of this piece
	 */
	public final ChessColor getColor() {
		return color;
	}
	
	/**
	 * Returns a set of all standard movements for this piece.
	 * 
	 * @return an unmodifiable set of movements for this piece
	 */
	public abstract ImmutableSet<Position> getStandardMoves();
	
	@Override public final boolean isEnemy(final Piece piece) {
		checkNotNull(piece);
		
		if (piece instanceof ChessPiece) {
			final ChessPiece other = (ChessPiece) piece;
			
			return color != other.color;
		}
		
		return false;
	}
	
	/**
	 * Returns a piece from the string representation.
	 * 
	 * @param board the board that will hold this piece
	 * @param input the piece representation
	 * @return a new piece
	 */
	protected static final ChessPiece fromString(final ChessBoard board, final char input) {
		checkNotNull(board);
		
		final ChessColor color = Character.isUpperCase(input) ? ChessColor.WHITE : ChessColor.BLACK;
		
		switch (Character.toUpperCase(input)) {
		case 'K':
			return new ChessKing(board, color);
		case 'Q':
			return new ChessQueen(board, color);
		case 'B':
			return new ChessBishop(board, color);
		case 'N':
			return new ChessKnight(board, color);
		case 'R':
			return new ChessRook(board, color);
		case 'P':
			return new ChessPawn(board, color);
		default:
			throw new IllegalArgumentException("Unknown chess piece " + input);
		}
	}
	
	@Override
	public final int hashCode() {
		return Objects.hashCode(Character.valueOf(notation), color);
	}
	
	@Override
	public final boolean equals(final Object obj) {
		if (getClass() == obj.getClass()) {
			final ChessPiece other = (ChessPiece) obj;
			
			return notation == other.notation && color == other.color;
		}
		
		return false;
	}
	
	@Override public final String toString() {
		return Character.toString(color.equals(ChessColor.WHITE) ? notation : Character.toLowerCase(notation));
	}
	
}
