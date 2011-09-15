package es.udc.pfc.gamelib.chess;

import static com.google.common.base.Preconditions.checkState;

import com.google.common.collect.ImmutableSet;

import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.ChessMovement.Special;
import es.udc.pfc.gamelib.chess.pieces.ChessBishop;
import es.udc.pfc.gamelib.chess.pieces.ChessKing;

public class MiniChessRules extends AbstractChessRules {

	private final ChessMovement checkMovement(final ChessPiece piece, final Position to) {
		final ChessMovement movement = new ChessMovement(piece.getPosition(), to, piece, board.getPieceAt(to));
		
		if (movement.getAttackedPiece() instanceof ChessKing) {
			movement.setSpecial(Special.CHECK);
			movement.setSpecial(Special.MATE);
		}
		
		return movement;
	}

	@Override
	protected final ImmutableSet<ChessMovement> getMovementsForPiece(ChessPiece piece) {
		checkState(board != null);
		
		final ImmutableSet.Builder<ChessMovement> moves = ImmutableSet.builder();
		
		if (piece instanceof ChessBishop) {
			for (final Position to : ((ChessBishop)piece).getMiniMoves()) {
				moves.add(checkMovement(piece, to));
			}
		}
		else {
			for (final Position to : piece.getStandardMoves()) {
				moves.add(checkMovement(piece, to));
			}
		}
		
		return moves.build();
	}
	
}
