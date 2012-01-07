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

public class ChessKingTest extends ChessPieceTest {
	
	@Override protected ChessKing addPiece(final Position position, final ChessColor color) {
		final ChessKing piece = new ChessKing(color);
		if (position != null) {
			assertNull(board.setPieceAt(position, piece));
		}
		return piece;
	}
	
	@Test public void testKingToString() {
		assertEquals("K", addPiece(null, ChessColor.WHITE).toString());
		assertEquals("k", addPiece(null, ChessColor.BLACK).toString());
	}
	
	@Test public void testKingNormal() {
		final ChessKing king = addPiece(new Position(3, 3), ChessColor.WHITE);
		final Set<Position> moves = king.getStandardMoves(board);
		
		assertEquals(8, moves.size());
		assertTrue(moves.contains(new Position(3, 4)));
		assertTrue(moves.contains(new Position(4, 4)));
		assertTrue(moves.contains(new Position(4, 3)));
		assertTrue(moves.contains(new Position(4, 2)));
		assertTrue(moves.contains(new Position(3, 2)));
		assertTrue(moves.contains(new Position(2, 2)));
		assertTrue(moves.contains(new Position(2, 3)));
		assertTrue(moves.contains(new Position(2, 4)));
	}
	
	@Test public void testKingCorners() {
		ChessKing king = addPiece(new Position(1, 1), ChessColor.WHITE);
		Set<Position> moves = king.getStandardMoves(board);
		
		assertEquals(3, moves.size());
		assertTrue(moves.contains(new Position(1, 2)));
		assertTrue(moves.contains(new Position(2, 2)));
		assertTrue(moves.contains(new Position(2, 1)));
		
		king = addPiece(new Position(5, 6), ChessColor.WHITE);
		moves = king.getStandardMoves(board);
		
		assertEquals(3, moves.size());
		assertTrue(moves.contains(new Position(5, 5)));
		assertTrue(moves.contains(new Position(4, 5)));
		assertTrue(moves.contains(new Position(4, 6)));
	}
	
	@Test public void testKingTeamBlocked() {
		final ChessKing king = addPiece(new Position(3, 3), ChessColor.WHITE);
		
		assertNull(board.setPieceAt(new Position(3, 4), new ChessRook(ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(4, 4), new ChessRook(ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(4, 3), new ChessRook(ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(4, 2), new ChessRook(ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(3, 2), new ChessRook(ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(2, 2), new ChessRook(ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(2, 3), new ChessRook(ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(2, 4), new ChessRook(ChessColor.WHITE)));
		
		assertEquals(0, king.getStandardMoves(board).size());
	}
	
	@Test public void testKingAttack() {
		final ChessKing king = addPiece(new Position(3, 3), ChessColor.WHITE);
		
		assertNull(board.setPieceAt(new Position(3, 4), new ChessRook(ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(4, 4), new ChessRook(ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(4, 3), new ChessRook(ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(4, 2), new ChessRook(ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(3, 2), new ChessRook(ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(2, 2), new ChessRook(ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(2, 3), new ChessRook(ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(2, 4), new ChessRook(ChessColor.BLACK)));
		
		final Set<Position> moves = king.getStandardMoves(board);
		assertEquals(8, moves.size());
		assertTrue(moves.contains(new Position(3, 4)));
		assertTrue(moves.contains(new Position(4, 4)));
		assertTrue(moves.contains(new Position(4, 3)));
		assertTrue(moves.contains(new Position(4, 2)));
		assertTrue(moves.contains(new Position(3, 2)));
		assertTrue(moves.contains(new Position(2, 2)));
		assertTrue(moves.contains(new Position(2, 3)));
		assertTrue(moves.contains(new Position(2, 4)));
	}
	
}
