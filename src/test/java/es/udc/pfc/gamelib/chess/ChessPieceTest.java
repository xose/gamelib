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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.udc.pfc.gamelib.board.Position;

public abstract class ChessPieceTest {
	
	protected ChessBoard board;
	
	protected abstract ChessPiece addPiece(Position pos, ChessColor color);
	
	@Before public void setUp() throws Exception {
		board = ChessBoard.fromString("5/5/5/5/5/5");
	}
	
	@Test public void testEnemy() {
		final ChessPiece p1 = addPiece(null, ChessColor.WHITE);
		final ChessPiece p2 = addPiece(null, ChessColor.WHITE);
		final ChessPiece p3 = addPiece(null, ChessColor.BLACK);
		final ChessPiece p4 = addPiece(null, ChessColor.BLACK);
		
		assertTrue(p1.isEnemy(p3));
		assertTrue(p4.isEnemy(p2));
		assertFalse(p1.isEnemy(p2));
		assertFalse(p3.isEnemy(p4));
	}
	
}
