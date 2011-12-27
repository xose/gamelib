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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public abstract class ChessGameTest {
	
	private static Random random = new Random();
	protected ChessGame game;
	
	protected ChessMovement randomMovement() {
		final ImmutableList<ChessMovement> possible = game.getPossibleMovements().asList();
		final ChessMovement randMove = possible.get(random.nextInt(possible.size()));
		final ChessMovement result = game.movePiece(randMove.getFrom(), randMove.getTo());
		assertEquals(randMove, result);
		return result;
	}
	
	@Test public void testStartStatus() {
		assertEquals(ChessColor.WHITE, game.getCurrentTurn());
		assertEquals(0, game.getMovements().size());
		assertTrue(game.getPossibleMovements().size() > 0);
		
		assertFalse(game.isFinished());
		assertNull(game.getWinner());
	}
	
	@Test public void testPossibleColor() {
		assertEquals(ChessColor.WHITE, game.getCurrentTurn());
		for (final ChessMovement possible : game.getPossibleMovements()) {
			final ChessPiece piece = game.getBoard().getPieceAt(possible.getFrom());
			assertNotNull(piece);
			assertEquals(ChessColor.WHITE, piece.getColor());
		}
		
		randomMovement();
		
		assertEquals(ChessColor.BLACK, game.getCurrentTurn());
		for (final ChessMovement possible : game.getPossibleMovements()) {
			final ChessPiece piece = game.getBoard().getPieceAt(possible.getFrom());
			assertNotNull(piece);
			assertEquals(ChessColor.BLACK, piece.getColor());
		}
	}
	
	@Test public void testChangeTurn() {
		assertEquals(ChessColor.WHITE, game.getCurrentTurn());
		randomMovement();
		assertEquals(ChessColor.BLACK, game.getCurrentTurn());
		randomMovement();
		assertEquals(ChessColor.WHITE, game.getCurrentTurn());
		randomMovement();
		assertEquals(ChessColor.BLACK, game.getCurrentTurn());
	}
	
	@Test public void testWinWhite() {
		game.setWinner(ChessColor.WHITE);
		
		assertTrue(game.isFinished());
		assertEquals(ChessColor.WHITE, game.getWinner());
		assertEquals(0, game.getPossibleMovements().size());
	}
	
	@Test public void testWinBlack() {
		game.setWinner(ChessColor.BLACK);
		
		assertTrue(game.isFinished());
		assertEquals(ChessColor.BLACK, game.getWinner());
		assertEquals(0, game.getPossibleMovements().size());
	}
	
	@Test public void testDraw() {
		game.draw();
		
		assertTrue(game.isFinished());
		assertNull(game.getWinner());
		assertEquals(0, game.getPossibleMovements().size());
	}
	
}
