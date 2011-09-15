package es.udc.pfc.gamelib.chess;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import com.google.common.collect.ImmutableSet;

public abstract class AbstractChessRules implements ChessRules {

	protected ChessBoard board;
	protected ChessColor turn;
	
	public AbstractChessRules() {
		turn = ChessColor.WHITE;
	}
	
	@Override
	public final void setBoard(final ChessBoard board) {
		this.board = checkNotNull(board);
	}
	
	@Override
	public final ChessColor nextTurn(){
		return turn = turn.other();
	}
	
	@Override
	public final ChessColor getCurrentTurn() {
		return turn;
	}
	
	abstract protected ImmutableSet<ChessMovement> getMovementsForPiece(ChessPiece piece);
	
	@Override
	public final ImmutableSet<ChessMovement> getPossibleMovements() {
		checkState(board != null);
		
		final ImmutableSet.Builder<ChessMovement> moves = ImmutableSet.builder();
		
		for (final ChessPiece piece : board.getAllPieces()) {
			if (!turn.equals(piece.getColor()))
				continue;
			
			moves.addAll(getMovementsForPiece(piece));
		}
		
		return moves.build();
	}
}
