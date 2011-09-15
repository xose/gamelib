package es.udc.pfc.gamelib.chess;

import com.google.common.collect.ImmutableSet;

public interface ChessRules {
	
	public void setBoard(ChessBoard board);
	
	public ChessColor getCurrentTurn();
	
	public ChessColor nextTurn();
	
	public ImmutableSet<ChessMovement> getPossibleMovements();
}
