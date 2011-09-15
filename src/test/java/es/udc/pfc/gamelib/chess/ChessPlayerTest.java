package es.udc.pfc.gamelib.chess;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ChessPlayerTest {
	
	private ChessGame game;
	
	@Before public void setUp() throws Exception {
		game = new ChessGame(ChessBoard.fromString(ChessBoard.CHESSBOARD_MINI), new MiniChessRules());
	}
	
	@Test public void testConstructor() {
		final ChessPlayer p = new ChessPlayer(game, "test", ChessColor.WHITE);
		
		assertEquals("test", p.getName());
		assertEquals(ChessColor.WHITE, p.getColor());
	}
	
	@Test(expected = NullPointerException.class) public void testConstructorNullGame() {
		new ChessPlayer(null, "test", ChessColor.WHITE);
	}
	
	@Test(expected = NullPointerException.class) public void testConstructorNullName() {
		new ChessPlayer(game, null, ChessColor.WHITE);
	}
	
	@Test(expected = NullPointerException.class) public void testConstructorNullColor() {
		new ChessPlayer(game, "test", null);
	}
	
	@Test public void testColor() {
		final ChessPlayer cp1 = new ChessPlayer(game, "white", ChessColor.WHITE);
		assertEquals(ChessColor.WHITE, cp1.getColor());
		
		final ChessPlayer cp2 = new ChessPlayer(game, "black", ChessColor.BLACK);
		assertEquals(ChessColor.BLACK, cp2.getColor());
	}
	
}
