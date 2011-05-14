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

import org.junit.Before;
import org.junit.Test;

public class ChessPlayerTest {

	private ChessGame game;

	@Before
	public void setUp() throws Exception {
		game = new ChessGame(ChessGame.ChessType.MiniChess);
	}

	@Test
	public void testConstructor() {
		final ChessPlayer p = new ChessPlayer(game, "test", ChessColor.WHITE);

		assertEquals("test", p.getName());
		assertEquals(ChessColor.WHITE, p.getColor());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullGame() {
		new ChessPlayer(null, "test", ChessColor.WHITE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullName() {
		new ChessPlayer(game, null, ChessColor.WHITE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNullColor() {
		new ChessPlayer(game, "test", null);
	}

	@Test
	public void testColor() {
		final ChessPlayer cp1 = new ChessPlayer(game, "white", ChessColor.WHITE);
		assertEquals(ChessColor.WHITE, cp1.getColor());

		final ChessPlayer cp2 = new ChessPlayer(game, "black", ChessColor.BLACK);
		assertEquals(ChessColor.BLACK, cp2.getColor());
	}

}
