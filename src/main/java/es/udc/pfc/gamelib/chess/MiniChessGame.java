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

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import es.udc.pfc.gamelib.board.Position;

/**
 * Represents a mini-chess game
 */
public final class MiniChessGame extends AbstractChessGame {
	
	public MiniChessGame() {
		super(ChessBoard.fromString(ChessBoard.CHESSBOARD_MINI));
	}
	
	@Override public final ImmutableSet<ChessMovement> getPossibleMoves(@Nullable final ChessColor color) {
		final ImmutableSet.Builder<ChessMovement> moves = ImmutableSet.builder();
		
		for (final ChessPiece piece : board.getAllPieces()) {
			if (color != null && !color.equals(piece.getColor())) {
				continue;
			}
			
			for (final Position to : piece.getStandardMoves()) {
				moves.add(new ChessMovement(piece.getPosition(), to, piece, board.getPieceAt(to)));
			}
		}
		
		return moves.build();
	}
	
	@Override public String toString() {
		return "MiniChess Game";
	}
	
}
