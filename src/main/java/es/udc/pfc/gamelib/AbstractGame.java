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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract game class
 * 
 * This class implements the common methods for all {@link Game} subclasses
 */
public abstract class AbstractGame<G extends Game<G, P>, P extends Player<G, P>> implements Game<G, P> {

	private final List<P> players;

	protected AbstractGame() {
		this.players = new ArrayList<P>();
	}

	@Override
	public final int getPlayerCount() {
		return players.size();
	}

	@Override
	public final List<P> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	/**
	 * Adds a new player to the game
	 * 
	 * @param player
	 *            the Player to add
	 * @return true if the player was added, false otherwise
	 */
	protected boolean addPlayer(final P player) {
		// No room for more players
		if (players.size() >= getMaxPlayerCount())
			return false;

		// Player is already playing, don't add it again
		if (players.contains(player))
			return false;

		return players.add(player);
	}

	/**
	 * Removes a player to the game
	 * 
	 * @param player
	 *            the player to remove
	 * @return true if the player was removed, false otherwise
	 */
	protected boolean removePlayer(final P player) {
		return players.remove(player);
	}

	@Override
	public abstract String toString();

}
