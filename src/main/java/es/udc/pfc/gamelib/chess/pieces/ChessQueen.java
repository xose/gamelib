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

package es.udc.pfc.gamelib.chess.pieces;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.ChessBoard;
import es.udc.pfc.gamelib.chess.ChessColor;
import es.udc.pfc.gamelib.chess.ChessPiece;

/**
 * Represents a chess Queen
 */
public final class ChessQueen extends ChessPiece {

	public ChessQueen(final ChessBoard board, final ChessColor color) {
		super(board, color);
	}

	@Override
	public final Set<Position> getAllMoves() {
		final HashSet<Position> moves = new HashSet<Position>();

		moves.addAll(getMovesTo(Direction.N));
		moves.addAll(getMovesTo(Direction.S));
		moves.addAll(getMovesTo(Direction.E));
		moves.addAll(getMovesTo(Direction.W));
		moves.addAll(getMovesTo(Direction.NE));
		moves.addAll(getMovesTo(Direction.NW));
		moves.addAll(getMovesTo(Direction.SE));
		moves.addAll(getMovesTo(Direction.SW));

		return Collections.unmodifiableSet(moves);
	}

	@Override
	public final String toString() {
		return getColor() == ChessColor.WHITE ? "Q" : "q";
	}

}
