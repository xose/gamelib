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

import es.udc.pfc.gamelib.board.AbstractMovement;
import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.pieces.ChessPawn;

/**
 * Represents a chess movement
 */
public final class ChessMovement extends AbstractMovement<ChessBoard, ChessMovement, ChessPiece> {

	private final ChessPiece attackedPiece;

	protected ChessMovement(final ChessPiece piece, final Position from, final Position to, final ChessPiece attackedPiece) {
		super(piece, from, to);

		this.attackedPiece = attackedPiece;
	}

	/**
	 * Returns the attacked piece for this movement
	 * 
	 * @return the attacked piece
	 */
	public final ChessPiece getAttackedPiece() {
		return attackedPiece;
	}

	@Override
	public final String toString() {
		return (getPiece() instanceof ChessPawn ? "" : getPiece().toString().toUpperCase()) + getFrom().toString() + (attackedPiece != null ? "x" : "-")
				+ getTo().toString();
	}
}
