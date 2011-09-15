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

import java.util.List;

import javax.annotation.Nullable;

/**
 * Represents a game
 */
public interface Game<P extends Player> {
	
	public enum Status {
		WAITING_FOR_PLAYERS, STARTED, FINISHED;
	}
	
	/**
	 * Adds a new listener to the game
	 * 
	 * @param listener
	 *            The new listener
	 */
	public void addListener(final Object listener);
	
	/**
	 * Returns the status of the game
	 * 
	 * @return the status of the game
	 */
	public Status getStatus();
	
	/**
	 * Returns the number of players currently playing
	 * 
	 * @return the number of players
	 */
	public int getPlayerCount();
	
	/**
	 * Returns an unmodifiable list of all the players in the game
	 * 
	 * @return a list of players
	 */
	public List<P> getPlayers();
	
	/**
	 * Returns the current player
	 * 
	 * @return the current player
	 */
	@Nullable public P getCurrentPlayer();
	
}
