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

public class ChessBishopTest extends ChessPieceTest {
	
	@Override protected ChessBishop addPiece(final Position position, final ChessColor color) {
		final ChessBishop piece = new ChessBishop(board, color);
		if (position != null) {
			assertNull(board.setPieceAt(position, piece));
		}
		return piece;
	}
	
	@Test public void testBishopToString() {
		assertEquals("B", addPiece(null, ChessColor.WHITE).toString());
		assertEquals("b", addPiece(null, ChessColor.BLACK).toString());
	}
	
	@Test public void testBishopNormal() {
		final ChessBishop bishop = addPiece(new Position(3, 3), ChessColor.WHITE);
		final Set<Position> moves = bishop.getStandardMoves();
		
		assertEquals(8, moves.size());
		assertTrue(moves.contains(new Position(4, 4)));
		assertTrue(moves.contains(new Position(5, 5)));
		assertTrue(moves.contains(new Position(4, 2)));
		assertTrue(moves.contains(new Position(5, 1)));
		assertTrue(moves.contains(new Position(2, 2)));
		assertTrue(moves.contains(new Position(1, 1)));
		assertTrue(moves.contains(new Position(2, 4)));
		assertTrue(moves.contains(new Position(1, 5)));
	}
	
	@Test public void testBishopTeamBlocked() {
		final ChessBishop bishop = addPiece(new Position(3, 3), ChessColor.WHITE);
		
		assertNull(board.setPieceAt(new Position(5, 5), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(4, 2), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(1, 1), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(1, 5), new ChessRook(board, ChessColor.WHITE)));
		
		final Set<Position> moves = bishop.getStandardMoves();
		assertEquals(3, moves.size());
		assertTrue(moves.contains(new Position(4, 4)));
		assertTrue(moves.contains(new Position(2, 2)));
		assertTrue(moves.contains(new Position(2, 4)));
	}
	
	@Test public void testBishopAttack() {
		final ChessBishop bishop = addPiece(new Position(3, 3), ChessColor.WHITE);
		
		assertNull(board.setPieceAt(new Position(5, 5), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(4, 2), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(1, 1), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(1, 5), new ChessRook(board, ChessColor.BLACK)));
		
		final Set<Position> moves = bishop.getStandardMoves();
		assertEquals(7, moves.size());
		assertTrue(moves.contains(new Position(4, 4)));
		assertTrue(moves.contains(new Position(5, 5)));
		assertTrue(moves.contains(new Position(4, 2)));
		assertTrue(moves.contains(new Position(2, 2)));
		assertTrue(moves.contains(new Position(1, 1)));
		assertTrue(moves.contains(new Position(2, 4)));
		assertTrue(moves.contains(new Position(1, 5)));
	}
}
