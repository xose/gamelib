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

import org.junit.Test;

import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.ChessColor;
import es.udc.pfc.gamelib.chess.ChessPieceTest;

public class ChessQueenTest extends ChessPieceTest {

	@Override
	protected ChessQueen addPiece(final Position position, final ChessColor color) {
		final ChessQueen piece = new ChessQueen(board, color);
		if (position != null) {
			assertNull(board.setPieceAt(position, piece));
		}
		return piece;
	}

	@Test
	public void testQueenToString() {
		assertEquals("Q", addPiece(null, ChessColor.WHITE).toString());
		assertEquals("q", addPiece(null, ChessColor.BLACK).toString());
	}

	@Test
	public void testQueenNormal() {
		final ChessQueen queen = addPiece(new Position(3, 3), ChessColor.WHITE);

		assertEquals(17, queen.getAllMoves().size());
		assertTrue(queen.canMove(new Position(3, 1)));
		assertTrue(queen.canMove(new Position(3, 2)));
		assertTrue(queen.canMove(new Position(3, 4)));
		assertTrue(queen.canMove(new Position(3, 5)));
		assertTrue(queen.canMove(new Position(3, 6)));
		assertTrue(queen.canMove(new Position(1, 3)));
		assertTrue(queen.canMove(new Position(2, 3)));
		assertTrue(queen.canMove(new Position(4, 3)));
		assertTrue(queen.canMove(new Position(5, 3)));

		assertTrue(queen.canMove(new Position(4, 4)));
		assertTrue(queen.canMove(new Position(5, 5)));
		assertTrue(queen.canMove(new Position(4, 2)));
		assertTrue(queen.canMove(new Position(5, 1)));
		assertTrue(queen.canMove(new Position(2, 2)));
		assertTrue(queen.canMove(new Position(1, 1)));
		assertTrue(queen.canMove(new Position(2, 4)));
		assertTrue(queen.canMove(new Position(1, 5)));
	}

	@Test
	public void testQueenTeamBlocked() {
		final ChessQueen queen = addPiece(new Position(3, 3), ChessColor.WHITE);

		assertNull(board.setPieceAt(new Position(3, 5), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(3, 2), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(1, 3), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(4, 3), new ChessRook(board, ChessColor.WHITE)));

		assertNull(board.setPieceAt(new Position(5, 5), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(4, 2), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(1, 1), new ChessRook(board, ChessColor.WHITE)));
		assertNull(board.setPieceAt(new Position(1, 5), new ChessRook(board, ChessColor.WHITE)));

		assertEquals(5, queen.getAllMoves().size());
		assertTrue(queen.canMove(new Position(3, 4)));
		assertTrue(queen.canMove(new Position(2, 3)));

		assertTrue(queen.canMove(new Position(4, 4)));
		assertTrue(queen.canMove(new Position(2, 2)));
		assertTrue(queen.canMove(new Position(2, 4)));
	}

	@Test
	public void testQueenAttack() {
		final ChessQueen queen = addPiece(new Position(3, 3), ChessColor.WHITE);

		assertNull(board.setPieceAt(new Position(3, 5), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(3, 2), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(1, 3), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(4, 3), new ChessRook(board, ChessColor.BLACK)));

		assertNull(board.setPieceAt(new Position(5, 5), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(4, 2), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(1, 1), new ChessRook(board, ChessColor.BLACK)));
		assertNull(board.setPieceAt(new Position(1, 5), new ChessRook(board, ChessColor.BLACK)));

		assertEquals(13, queen.getAllMoves().size());
		assertTrue(queen.canMove(new Position(3, 2)));
		assertTrue(queen.canMove(new Position(3, 4)));
		assertTrue(queen.canMove(new Position(3, 5)));
		assertTrue(queen.canMove(new Position(1, 3)));
		assertTrue(queen.canMove(new Position(2, 3)));
		assertTrue(queen.canMove(new Position(4, 3)));

		assertTrue(queen.canMove(new Position(4, 4)));
		assertTrue(queen.canMove(new Position(5, 5)));
		assertTrue(queen.canMove(new Position(4, 2)));
		assertTrue(queen.canMove(new Position(2, 2)));
		assertTrue(queen.canMove(new Position(1, 1)));
		assertTrue(queen.canMove(new Position(2, 4)));
		assertTrue(queen.canMove(new Position(1, 5)));
	}
}
