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

package es.udc.pfc.gamelib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public abstract class GameTest<G extends AbstractGame<G, P>, P extends AbstractPlayer<G, P>> {

	protected G game;

	protected abstract int expectedMinPlayers();

	protected abstract int expectedMaxPlayers();

	protected abstract G getNewGame();

	protected abstract boolean addNewPlayer(String name);

	@Before
	public void setUp() throws Exception {
		this.game = getNewGame();
	}

	@Test
	public void testPlayerNumber() {
		assertEquals(game.getMinPlayerCount(), expectedMinPlayers());
		assertEquals(game.getMaxPlayerCount(), expectedMaxPlayers());
	}

	@Test
	public void testEmptyPlayers() {
		assertEquals(game.getPlayerCount(), 0);
	}

	@Test
	public void testCurrentPlayerNull() {
		assertNull(game.getCurrentPlayer());
	}

	@Test
	public void testAddPlayers() {
		for (int i = 1; i <= game.getMaxPlayerCount(); i++) {
			assertTrue(addNewPlayer("test " + i));
			assertEquals(game.getPlayerCount(), i);
		}

		assertFalse(addNewPlayer("fail"));
		assertEquals(game.getPlayerCount(), game.getMaxPlayerCount());
	}

	@Test
	public void testCurrentPlayer() {
		testAddPlayers();

		final P current = game.getCurrentPlayer();

		assertNotNull(current);
		assertEquals(current.getName(), "test 1");

		assertTrue(current.isCurrentTurn());
	}

	@Test
	public void testRemovePlayer() {
		testAddPlayers();

		assertEquals(game.getPlayerCount(), game.getMaxPlayerCount());
		assertTrue(game.removePlayer(game.getCurrentPlayer()));
		assertEquals(game.getPlayerCount(), game.getMaxPlayerCount() - 1);
	}

}
