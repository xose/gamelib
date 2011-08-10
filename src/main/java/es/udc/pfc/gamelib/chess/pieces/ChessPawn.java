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
 * Represents a chess Pawn
 */
public final class ChessPawn extends ChessPiece {
	
	public ChessPawn(final ChessBoard board, final ChessColor color) {
		super(board, color, 'P');
	}
	
	@Override public final ImmutableSet<Position> getStandardMoves() {
		final ImmutableSet.Builder<Position> moves = ImmutableSet.builder();
		
		final int s = getColor() == ChessColor.WHITE ? 1 : -1;
		
		final Position f = getPosition().relative(0, s);
		if (board.isValidPosition(f) && !board.isPieceAt(f)) {
			moves.add(f);
		}
		
		final Position d1 = getPosition().relative(-1, s);
		if (board.isValidPosition(d1) && board.isPieceAt(d1) && isEnemy(board.getPieceAt(d1))) {
			moves.add(d1);
		}
		
		final Position d2 = getPosition().relative(+1, s);
		if (board.isValidPosition(d2) && board.isPieceAt(d2) && isEnemy(board.getPieceAt(d2))) {
			moves.add(d2);
		}
		
		return moves.build();
	}
	
}
