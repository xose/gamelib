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

package es.udc.pfc.gamelib.board;

import java.util.List;

import com.google.common.collect.ImmutableSet;

import es.udc.pfc.gamelib.Game;
import es.udc.pfc.gamelib.Player;

/**
 * Represents a board game
 */
public interface BoardGame<B extends Board<?>, M extends Movement, P extends Player> extends Game<P> {
	
	/**
	 * Returns the board for this game
	 * 
	 * @return the board for this game
	 */
	public B getBoard();
	
	/**
	 * Returns the list of movements made in this game
	 * 
	 * @return an unmodifiable list of the movements in this game
	 */
	public List<M> getMovements();
	
	/**
	 * Returns all the possible moves for all the pieces in the board
	 * 
	 * @return an unmodifiable set of all possible moves
	 */
	public ImmutableSet<M> getPossibleMoves();
	
}
