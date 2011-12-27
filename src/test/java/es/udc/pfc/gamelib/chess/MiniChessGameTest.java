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
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.ChessMovement.Special;

public class MiniChessGameTest extends ChessGameTest {
	
	@Before public void setUp() throws Exception {
		game = new MiniChessGame();
	}
	
	@Test public void testBoard() {
		assertEquals(ChessBoard.CHESSBOARD_MINI, game.getBoard().toString());
	}
	
	@Test public void testFullGame() {
		assertNotNull(game.movePiece(new Position(3, 2), new Position(3, 3)));
		assertNotNull(game.movePiece(new Position(2, 5), new Position(2, 4)));
		assertNotNull(game.movePiece(new Position(4, 1), new Position(1, 4)));
		assertNotNull(game.movePiece(new Position(5, 6), new Position(4, 4)));
		assertNotNull(game.movePiece(new Position(1, 4), new Position(3, 6)));
		assertEquals(5, game.getMovements().size());
		assertTrue(game.getMovements().get(4).is(Special.MATE));
	}
	
}
