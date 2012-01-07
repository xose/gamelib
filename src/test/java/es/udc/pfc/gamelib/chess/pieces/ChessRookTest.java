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

package es.udc.pfc.gamelib.chess.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.ChessColor;
import es.udc.pfc.gamelib.chess.ChessPieceTest;

public class ChessRookTest extends ChessPieceTest {
	
	@Override protected ChessRook addPiece(final Position position, final ChessColor color) {
		final ChessRook piece = new ChessRook(color);
		if (position != null) {
			assertNull(board.setPieceAt(position, piece));
		}
		return piece;
	}
	
	@Test public void testRookToString() {
		assertEquals("R", addPiece(null, ChessColor.WHITE).toString());
		assertEquals("r", addPiece(null, ChessColor.BLACK).toString());
	}
	
	@Test public void testRookNormal() {
		final ChessRook rook = addPiece(new Position(3, 3), ChessColor.WHITE);
		final Set<Position> moves = rook.getStandardMoves(board);
		
		assertEquals(9, moves.size());
		assertTrue(moves.contains(new Position(3, 1)));
		assertTrue(moves.contains(new Position(3, 2)));
		assertTrue(moves.contains(new Position(3, 4)));
		assertTrue(moves.contains(new Position(3, 5)));
		assertTrue(moves.contains(new Position(3, 6)));
		assertTrue(moves.contains(new Position(1, 3)));
		assertTrue(moves.contains(new Position(2, 3)));
		assertTrue(moves.contains(new Position(4, 3)));
		assertTrue(moves.contains(new Position(5, 3)));
	}
	
	@Test public void testRookTeamBlocked() {
		final ChessRook rook = addPiece(new Position(3, 3), ChessColor.WHITE);
		
		assertNull(board.setPieceAt(new Position(3, 5), new ChessRook(ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(3, 2), new ChessRook(ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(1, 3), new ChessRook(ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(4, 3), new ChessRook(ChessColor.WHITE)));
		
		final Set<Position> moves = rook.getStandardMoves(board);
		assertEquals(2, moves.size());
		assertTrue(moves.contains(new Position(3, 4)));
		assertTrue(moves.contains(new Position(2, 3)));
	}
	
	@Test public void testRookAttack() {
		final ChessRook rook = addPiece(new Position(3, 3), ChessColor.WHITE);
		
		assertNull(board.setPieceAt(new Position(3, 5), new ChessRook(ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(3, 2), new ChessRook(ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(1, 3), new ChessRook(ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(4, 3), new ChessRook(ChessColor.BLACK)));
		
		final Set<Position> moves = rook.getStandardMoves(board);
		assertEquals(6, moves.size());
		assertTrue(moves.contains(new Position(3, 2)));
		assertTrue(moves.contains(new Position(3, 4)));
		assertTrue(moves.contains(new Position(3, 5)));
		assertTrue(moves.contains(new Position(1, 3)));
		assertTrue(moves.contains(new Position(2, 3)));
		assertTrue(moves.contains(new Position(4, 3)));
	}
}
