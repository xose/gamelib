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

/**
 * Abstract player class
 * 
 * This class implements the common methods for all {@link Player} subclasses
 */
public abstract class AbstractPlayer<G extends Game<G, P>, P extends Player<G, P>> implements Player<G, P> {

	private final G game;
	private final String name;

	protected AbstractPlayer(final G game, final String name) {
		if (game == null)
			throw new IllegalArgumentException("game cannot be null");
		if (name == null)
			throw new IllegalArgumentException("name cannot be null");

		this.game = game;
		this.name = name;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public boolean isCurrentTurn() {
		return game.getCurrentPlayer().equals(this);
	}

	@Override
	public abstract String toString();

}
