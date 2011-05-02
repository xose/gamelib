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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.ChessColor;
import es.udc.pfc.gamelib.chess.ChessPieceTest;

public class ChessPawnTest extends ChessPieceTest {

	@Override
	protected ChessPawn addPiece(final Position position, final ChessColor color) {
		final ChessPawn piece = new ChessPawn(board, color);
		if (position != null) {
			assertNull(board.setPieceAt(position, piece));
		}
		return piece;
	}

	@Test
	public void testPawnToString() {
		assertEquals("P", addPiece(null, ChessColor.WHITE).toString());
		assertEquals("p", addPiece(null, ChessColor.BLACK).toString());
	}

	@Test
	public void testWhitePawnNormal() {
		final ChessPawn pawn = addPiece(new Position(2, 2), ChessColor.WHITE);

		assertEquals(1, pawn.getAllMoves().size());
		assertTrue(pawn.canMove(new Position(2, 3)));
	}

	@Test
	public void testWhitePawnTeamBlocked() {
		final ChessPawn pawn = addPiece(new Position(2, 2), ChessColor.WHITE);

		assertNull(board.setPieceAt(new Position(1, 3), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(2, 3), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(3, 3), new ChessRook(board, ChessColor.WHITE)));
		assertEquals(0, pawn.getAllMoves().size());
		assertNotNull(board.setPieceAt(new Position(2, 3), null));
	}

	@Test
	public void testWhitePawnBlocked() {
		final ChessPawn pawn = addPiece(new Position(2, 2), ChessColor.WHITE);

		assertNull(board.setPieceAt(new Position(2, 3), new ChessRook(board, ChessColor.BLACK)));
		assertEquals(0, pawn.getAllMoves().size());
		assertNotNull(board.setPieceAt(new Position(2, 3), null));
	}

	@Test
	public void testWhitePawnAttackL() {
		final ChessPawn pawn = addPiece(new Position(2, 2), ChessColor.WHITE);

		assertNull(board.setPieceAt(new Position(1, 3), new ChessRook(board, ChessColor.BLACK)));
		assertEquals(2, pawn.getAllMoves().size());
		assertTrue(pawn.canMove(new Position(2, 3)));
		assertTrue(pawn.canMove(new Position(1, 3)));
		assertNotNull(board.setPieceAt(new Position(1, 3), null));
	}

	@Test
	public void testWhitePawnAttackR() {
		final ChessPawn pawn = addPiece(new Position(2, 2), ChessColor.WHITE);

		assertNull(board.setPieceAt(new Position(3, 3), new ChessRook(board, ChessColor.BLACK)));
		assertEquals(2, pawn.getAllMoves().size());
		assertTrue(pawn.canMove(new Position(2, 3)));
		assertTrue(pawn.canMove(new Position(3, 3)));
		assertNotNull(board.setPieceAt(new Position(3, 3), null));
	}

	@Test
	public void testWhitePawnAttackLR() {
		final ChessPawn pawn = addPiece(new Position(2, 2), ChessColor.WHITE);

		assertNull(board.setPieceAt(new Position(1, 3), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(3, 3), new ChessRook(board, ChessColor.BLACK)));
		assertEquals(3, pawn.getAllMoves().size());
		assertTrue(pawn.canMove(new Position(1, 3)));
		assertTrue(pawn.canMove(new Position(2, 3)));
		assertTrue(pawn.canMove(new Position(3, 3)));
		assertNotNull(board.setPieceAt(new Position(1, 3), null));
		assertNotNull(board.setPieceAt(new Position(3, 3), null));
	}

	@Test
	public void testWhitePawnBorder() {
		final ChessPawn pawn = addPiece(new Position(2, 6), ChessColor.WHITE);

		assertEquals(0, pawn.getAllMoves().size());
	}

	@Test
	public void testBlackPawnNormal() {
		final ChessPawn pawn = addPiece(new Position(2, 5), ChessColor.BLACK);

		assertEquals(1, pawn.getAllMoves().size());
		assertTrue(pawn.canMove(new Position(2, 4)));
	}

	@Test
	public void testBlackPawnTeamBlocked() {
		final ChessPawn pawn = addPiece(new Position(2, 5), ChessColor.BLACK);

		assertNull(board.setPieceAt(new Position(1, 4), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(2, 4), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(3, 4), new ChessRook(board, ChessColor.BLACK)));
		assertEquals(0, pawn.getAllMoves().size());
		assertNotNull(board.setPieceAt(new Position(2, 4), null));
	}

	@Test
	public void testBlackPawnBlocked() {
		final ChessPawn pawn = addPiece(new Position(2, 5), ChessColor.BLACK);

		assertNull(board.setPieceAt(new Position(2, 4), new ChessRook(board, ChessColor.WHITE)));
		assertEquals(0, pawn.getAllMoves().size());
		assertNotNull(board.setPieceAt(new Position(2, 4), null));
	}

	@Test
	public void testBlackPawnAttackL() {
		final ChessPawn pawn = addPiece(new Position(2, 5), ChessColor.BLACK);

		assertNull(board.setPieceAt(new Position(1, 4), new ChessRook(board, ChessColor.WHITE)));
		assertEquals(2, pawn.getAllMoves().size());
		assertTrue(pawn.canMove(new Position(2, 4)));
		assertTrue(pawn.canMove(new Position(1, 4)));
		assertNotNull(board.setPieceAt(new Position(1, 4), null));
	}

	@Test
	public void testBlackPawnAttackR() {
		final ChessPawn pawn = addPiece(new Position(2, 5), ChessColor.BLACK);

		assertNull(board.setPieceAt(new Position(3, 4), new ChessRook(board, ChessColor.WHITE)));
		assertEquals(2, pawn.getAllMoves().size());
		assertTrue(pawn.canMove(new Position(2, 4)));
		assertTrue(pawn.canMove(new Position(3, 4)));
		assertNotNull(board.setPieceAt(new Position(3, 4), null));
	}

	@Test
	public void testBlackPawnAttackLR() {
		final ChessPawn pawn = addPiece(new Position(2, 5), ChessColor.BLACK);

		assertNull(board.setPieceAt(new Position(1, 4), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(3, 4), new ChessRook(board, ChessColor.WHITE)));
		assertEquals(3, pawn.getAllMoves().size());
		assertTrue(pawn.canMove(new Position(1, 4)));
		assertTrue(pawn.canMove(new Position(2, 4)));
		assertTrue(pawn.canMove(new Position(3, 4)));
		assertNotNull(board.setPieceAt(new Position(1, 4), null));
		assertNotNull(board.setPieceAt(new Position(3, 4), null));
	}

	@Test
	public void testBlackPawnBorder() {
		final ChessPawn pawn = addPiece(new Position(2, 1), ChessColor.BLACK);

		assertEquals(0, pawn.getAllMoves().size());
	}

}
