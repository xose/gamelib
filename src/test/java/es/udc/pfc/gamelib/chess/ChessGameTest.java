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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import es.udc.pfc.gamelib.GameTest;
import es.udc.pfc.gamelib.board.InvalidMovementException;
import es.udc.pfc.gamelib.board.Position;

public class ChessGameTest extends GameTest<AbstractChessGame, ChessPlayer> {
	
	@Override protected int expectedMinPlayers() {
		return 2;
	}
	
	@Override protected int expectedMaxPlayers() {
		return 2;
	}
	
	@Override protected AbstractChessGame getNewGame() {
		return new MiniChessGame();
	}
	
	@Override protected boolean addNewPlayer(final String name) {
		return game.addPlayer(name);
	}
	
	@Test public void testCurrentTurn() {
		assertNull(game.getCurrentTurn());
		assertTrue(game.addPlayer("white player"));
		assertNull(game.getCurrentTurn());
		assertTrue(game.addPlayer("black player"));
		assertNotNull(game.getCurrentTurn());
		
		assertEquals(ChessColor.WHITE, game.getCurrentTurn());
	}
	
	@Test public void testChessCurrentPlayer() {
		assertNull(game.getCurrentPlayer());
		assertTrue(game.addPlayer("white player"));
		assertNull(game.getCurrentPlayer());
		assertTrue(game.addPlayer("black player"));
		assertNotNull(game.getCurrentPlayer());
		
		assertEquals("white player", game.getCurrentPlayer().getName());
		assertEquals(ChessColor.WHITE, game.getCurrentPlayer().getColor());
	}
	
	@Test public void testMovement() {
		testAddPlayers();
		
		assertEquals(ChessColor.WHITE, game.getCurrentTurn());
		
		try {
			game.movePiece(new Position(4, 2), new Position(4, 3));
			game.movePiece(new Position(3, 5), new Position(3, 4));
		} catch (final InvalidMovementException e) {
			fail(e.getMessage());
		}
	}
	
}
