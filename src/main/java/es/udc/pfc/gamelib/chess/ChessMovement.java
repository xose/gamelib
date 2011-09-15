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

import java.util.EnumSet;

import javax.annotation.Nullable;

import es.udc.pfc.gamelib.board.AbstractMovement;
import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.pieces.ChessPawn;

/**
 * Represents a chess movement
 */
public final class ChessMovement extends AbstractMovement {
	
	protected enum Special {
		CHECK, MATE, CASTLE_K, CASTLE_Q;
	}
	
	private final ChessPiece sourcePiece;
	@Nullable private final ChessPiece attackedPiece;
	private final EnumSet<Special> special;
	
	protected ChessMovement(final Position from, final Position to, final ChessPiece sourcePiece, @Nullable final ChessPiece attackedPiece) {
		super(from, to);
		
		this.sourcePiece = checkNotNull(sourcePiece);
		this.attackedPiece = attackedPiece;
		this.special = EnumSet.noneOf(Special.class);
		
		if (attackedPiece != null) {
			checkArgument(sourcePiece.isEnemy(attackedPiece));
		}
	}
	
	/**
	 * Returns the piece involved in the movement
	 * 
	 * @return the piece for this movement
	 */
	public final ChessPiece getSourcePiece() {
		return attackedPiece;
	}
	
	/**
	 * Returns the attacked piece for this movement
	 * 
	 * @return the attacked piece
	 */
	@Nullable public final ChessPiece getAttackedPiece() {
		return attackedPiece;
	}
	
	protected final boolean setSpecial(Special special) {
		return this.special.add(special);
	}
	
	public final boolean is(Special special) {
		return this.special.contains(special);
	}
	
	@Override public final String toString() {
		if (is(Special.CASTLE_K))
			return "O-O";
		if (is(Special.CASTLE_Q))
			return "O-O-O";
		
		final StringBuilder builder = new StringBuilder();
		
		if (!(sourcePiece instanceof ChessPawn)) {
			builder.append(sourcePiece.toString().toUpperCase());
		}
		
		builder.append(from).append(attackedPiece != null ? 'x' : '-').append(to);
		
		if (is(Special.CHECK)) {
			builder.append('+');
		}
		if (is(Special.MATE)) {
			builder.append('+');
		}
		
		return builder.toString();
	}
}
