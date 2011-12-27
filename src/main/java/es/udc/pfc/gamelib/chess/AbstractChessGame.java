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

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

public abstract class AbstractChessGame implements ChessGame {
	
	private enum Winner { WHITE, BLACK, DRAW; }

	protected final ChessBoard chessBoard;
	protected final List<ChessMovement> movements;
	protected ChessColor currentTurn;
	@Nullable private Winner winner; 
	
	protected AbstractChessGame(final ChessBoard board) {
		this.chessBoard = checkNotNull(board);
		this.movements = Lists.newArrayList();
		currentTurn = ChessColor.WHITE;
	}
	
	@Override
	public final ChessBoard getBoard() {
		return chessBoard;
	}
	
	@Override
	public final List<ChessMovement> getMovements() {
		return Collections.unmodifiableList(movements);
	}
	
	@Override
	public final ChessColor getCurrentTurn() {
		checkState(winner == null);
		
		return currentTurn;
	}
	
	@Override
	public final boolean isFinished() {
		return winner != null;
	}
	
	@Override
	public final ChessColor getWinner() {
		if (winner == null || winner == Winner.DRAW)
			return null;
		
		return winner == Winner.WHITE ? ChessColor.WHITE : ChessColor.BLACK;
	}
	
	@Override
	public final void setWinner(final ChessColor color) {
		checkState(winner == null);
		
		this.winner = checkNotNull(color) == ChessColor.WHITE ? Winner.WHITE : Winner.BLACK;
	}
	
	@Override
	public final void draw() {
		checkState(winner == null);
		
		this.winner = Winner.DRAW;
	}
	
}
