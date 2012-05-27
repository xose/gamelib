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

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

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
@Immutable
public abstract class ChessPiece extends AbstractPiece {
	
	protected static enum Type {
		King('K'),
		Queen('Q'),
		Rook('R'),
		Bishop('B'),
		Knight('N'),
		Pawn('P');
		
		private final char notation;
		
		private Type(final char notation) {
			this.notation = notation;
		}
		
		public final char getNotation() {
			return notation;
		}
		
		public final char getNotationForColor(final ChessColor color) {
			return color == ChessColor.WHITE ? notation : Character.toLowerCase(notation);
		}
		
		@Nullable public static final Type fromNotation(final char ch) {
			final char notation = Character.toUpperCase(ch);
			for (final Type type : values()) {
				if (notation == type.getNotation())
					return type;
			}
			return null;
		}
	}
	
	private final Type type;
	private final ChessColor color;
	
	protected ChessPiece(final Type type, final ChessColor color) {
		this.type = checkNotNull(type);
		this.color = checkNotNull(color);
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
	public abstract ImmutableSet<Position> getStandardMoves(ChessBoard board);
	
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
			return new ChessKing(color);
		case 'Q':
			return new ChessQueen(color);
		case 'B':
			return new ChessBishop(color);
		case 'N':
			return new ChessKnight(color);
		case 'R':
			return new ChessRook(color);
		case 'P':
			return new ChessPawn(color);
		default:
			throw new IllegalArgumentException("Unknown chess piece " + input);
		}
	}
	
	@Override
	public final int hashCode() {
		return Objects.hashCode(type, color);
	}
	
	@Override
	public final boolean equals(@Nullable final Object obj) {
		if (obj == null)
			return false;
		
		if (getClass() == obj.getClass()) {
			final ChessPiece other = (ChessPiece) obj;
			
			return type == other.type && color == other.color;
		}
		
		return false;
	}
	
	@Override public final String toString() {
		return String.valueOf(type.getNotationForColor(color));
	}
	
}
