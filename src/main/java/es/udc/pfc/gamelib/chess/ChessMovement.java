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

import javax.annotation.Nullable;

import es.udc.pfc.gamelib.board.AbstractMovement;
import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.pieces.ChessPawn;

/**
 * Represents a chess movement
 */
public final class ChessMovement extends AbstractMovement {
	
	protected enum Special {
		NONE, CHECK, MATE;
	}
	
	private final ChessPiece sourcePiece;
	@Nullable private final ChessPiece attackedPiece;
	private final Special special;
	
	protected ChessMovement(final Position from, final Position to, final ChessPiece sourcePiece, @Nullable final ChessPiece attackedPiece) {
		this(from, to, sourcePiece, attackedPiece, Special.NONE);
	}
	
	protected ChessMovement(final Position from, final Position to, final ChessPiece sourcePiece, @Nullable final ChessPiece attackedPiece,
			final Special special) {
		super(from, to);
		
		this.sourcePiece = checkNotNull(sourcePiece);
		this.attackedPiece = attackedPiece;
		this.special = checkNotNull(special);
		
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
	
	public final boolean isCheck() {
		return special == Special.CHECK || special == Special.MATE;
	}
	
	public final boolean isMate() {
		return special == Special.MATE;
	}
	
	@Override public final String toString() {
		final StringBuilder builder = new StringBuilder();
		
		if (!(sourcePiece instanceof ChessPawn)) {
			builder.append(sourcePiece.toString().toUpperCase());
		}
		
		builder.append(from).append(attackedPiece != null ? 'x' : '-').append(to);
		
		if (isCheck()) {
			builder.append('+');
		}
		if (isMate()) {
			builder.append('+');
		}
		
		return builder.toString();
	}
}
