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
import static com.google.common.base.Preconditions.checkState;

import java.util.EnumSet;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;

import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.ChessMovement.Special;
import es.udc.pfc.gamelib.chess.pieces.ChessBishop;
import es.udc.pfc.gamelib.chess.pieces.ChessKing;
import es.udc.pfc.gamelib.chess.pieces.ChessPawn;
import es.udc.pfc.gamelib.chess.pieces.ChessQueen;

public final class MiniChessGame extends AbstractChessGame {
	
	@Nullable private ImmutableSet<ChessMovement> possibleMovementsCache;
	
	public MiniChessGame() {
		super(ChessBoard.fromString(ChessBoard.CHESSBOARD_MINI));
	}
	
	private final ChessMovement checkMovement(final Position from, final Position to) {
		final EnumSet<ChessMovement.Special> special = EnumSet.noneOf(ChessMovement.Special.class);
		if (chessBoard.getPieceAt(to) instanceof ChessKing) {
			special.add(Special.CHECK);
			special.add(Special.MATE);
		}
		
		return new ChessMovement(from, to, chessBoard.getPieceAt(from), chessBoard.getPieceAt(to), special);
	}

	private final ImmutableSet<ChessMovement> getPossibleMovements(final ChessBoard board, final ChessColor turn) {
		final ImmutableSet.Builder<ChessMovement> moves = ImmutableSet.builder();
		
		for (final ChessPiece piece : board.getAllPieces()) {
			if (!currentTurn.equals(piece.getColor()))
				continue;
			
			final Position from = board.getPositionFor(piece);
			
			if (piece instanceof ChessBishop) {
				for (final Position to : ((ChessBishop)piece).getMiniMoves(board)) {
					moves.add(checkMovement(from, to));
				}
			}
			else {
				for (final Position to : piece.getStandardMoves(board)) {
					moves.add(checkMovement(from, to));
				}
			}
		}
		
		return moves.build();
	}
	
	@Override
	public final ImmutableSet<ChessMovement> getPossibleMovements() {
		if (isFinished())
			return ImmutableSet.of();
		
		if (possibleMovementsCache == null) {
			possibleMovementsCache = getPossibleMovements(chessBoard, currentTurn);
		}
		
		return possibleMovementsCache;
	}
	
	@Override
	@Nullable
	public final ChessMovement movePiece(final Position from, final Position to) {
		checkState(!isFinished());
		checkNotNull(from);
		checkNotNull(to);
		
		for (final ChessMovement movement : getPossibleMovements()) {
			if (!from.equals(movement.getFrom()) || !to.equals(movement.getTo()))
				continue;
			
			chessBoard.setPieceAt(to, chessBoard.setPieceAt(from, null));
			
			if (chessBoard.getPieceAt(to) instanceof ChessPawn && (to.getRow() == 1 || to.getRow() == chessBoard.getNumberOfRows())) {
				chessBoard.setPieceAt(to, new ChessQueen(currentTurn));
			}
			
			currentTurn = currentTurn.other();
			possibleMovementsCache = null;
			
			if (movement.is(Special.MATE)) {
				setWinner(movement.getSourcePiece().getColor());
			}
			
			movements.add(movement);
			
			return movement;
		}
		
		return null;
	}
	
}
