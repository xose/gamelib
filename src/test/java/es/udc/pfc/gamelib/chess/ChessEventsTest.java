package es.udc.pfc.gamelib.chess;

import org.junit.Test;

import com.google.common.eventbus.Subscribe;

import es.udc.pfc.gamelib.PlayerAddedEvent;

public class ChessEventsTest {
	
	@Test public void testPlayerAdded() {
		final ChessGame game = new ChessGame(ChessBoard.fromString(ChessBoard.CHESSBOARD_MINI), new MiniChessRules());
		game.addListener(this);
		
		game.addPlayer("test 1");
		game.addPlayer("test 2");
	}
	
	@Subscribe public void playerAdded(final PlayerAddedEvent<ChessPlayer> event) {
		System.out.println("Player added: " + event.getPlayer().getName());
	}
	
}
