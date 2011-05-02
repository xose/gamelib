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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.udc.pfc.gamelib.board.Position;
import es.udc.pfc.gamelib.chess.pieces.ChessKing;

public class ChessBoardTest {

	private ChessBoard board;

	@Before
	public void setUp() throws Exception {
		board = ChessBoard.getMiniChessBoard();
	}

	@Test
	public void testBoardSize() {
		assertEquals(5, board.getNumberOfColumns());
		assertEquals(6, board.getNumberOfRows());
	}

	@Test
	public void testValidPosition() {
		assertTrue(board.isValidPosition(new Position(1, 1)));
		assertTrue(board.isValidPosition(new Position(5, 5)));
		assertTrue(board.isValidPosition(new Position(3, 2)));

		assertFalse(board.isValidPosition(new Position(0, 0)));
		assertFalse(board.isValidPosition(new Position(6, 3)));
		assertFalse(board.isValidPosition(new Position(2, 7)));
		assertFalse(board.isValidPosition(new Position(6, 7)));
	}

	@Test
	public void testGetAllPieces() {
		assertEquals(20, board.getAllPieces().size());
	}

	@Test
	public void testGetPiece() {
		assertNull(board.getPieceAt(new Position(3, 3)));

		assertEquals(ChessKing.class, board.getPieceAt(new Position(3, 1)).getClass());
	}

	@Test
	public void testGetMovements() {
		assertEquals(12, board.getPossibleMoves().size());
		assertEquals(6, board.getPossibleMoves(ChessColor.WHITE).size());
		assertEquals(6, board.getPossibleMoves(ChessColor.BLACK).size());
	}

}
