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

import com.google.common.collect.ImmutableSet;

import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.ChessBoard;
import es.udc.pfc.gamelib.chess.ChessColor;
import es.udc.pfc.gamelib.chess.ChessPiece;

/**
 * Represents a chess Bishop.
 */
public final class ChessBishop extends ChessPiece {
	
	private final int[][] special = { { 0, +1 }, { 0, -1 }, { +1, 0 }, { -1, 0 } };
	
	public ChessBishop(final ChessColor color) {
		super(Type.Bishop, color);
	}
	
	@Override public ImmutableSet<Position> getStandardMoves(final ChessBoard board) {
		final ImmutableSet.Builder<Position> moves = ImmutableSet.builder();
		
		moves.addAll(getMovesTo(board, Direction.NE));
		moves.addAll(getMovesTo(board, Direction.NW));
		moves.addAll(getMovesTo(board, Direction.SE));
		moves.addAll(getMovesTo(board, Direction.SW));
		
		return moves.build();
	}

	public final ImmutableSet<Position> getMiniMoves(final ChessBoard board) {
		final ImmutableSet.Builder<Position> moves = ImmutableSet.builder();
		
		moves.addAll(getStandardMoves(board));
		
		for (final int[] element : special) {
			final Position pos = board.getPositionFor(this).relative(element[0], element[1]);
			
			if (board.isValidPosition(pos) && !board.isPieceAt(pos)) {
				moves.add(pos);
			}
		}
		
		return moves.build();
	}
	
}
