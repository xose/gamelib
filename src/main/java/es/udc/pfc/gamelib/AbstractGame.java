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

import com.google.common.eventbus.EventBus;

/**
 * Abstract game class
 * 
 * This class implements the common methods for all {@link Game} subclasses
 */
public abstract class AbstractGame<P extends Player> implements Game<P> {
	
	private final EventBus eventBus;
	
	protected final List<P> players;
	protected Status status;
	
	protected AbstractGame() {
		this.eventBus = new EventBus(this.getClass().getName());
		this.players = new ArrayList<P>();
		this.status = Status.WAITING_FOR_PLAYERS;
	}
	
	@Override public final Status getStatus() {
		return status;
	}
	
	/**
	 * Adds a new listener to the game
	 * 
	 * @param listener
	 *            The new listener
	 */
	public final void addListener(final Object listener) {
		eventBus.register(listener);
	}
	
	protected final void postEvent(final Object event) {
		eventBus.post(event);
	}
	
	@Override public final int getPlayerCount() {
		return players.size();
	}
	
	@Override public final List<P> getPlayers() {
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
	
	@Override public abstract String toString();
	
}
