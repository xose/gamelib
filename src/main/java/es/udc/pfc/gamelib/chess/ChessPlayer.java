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

import es.udc.pfc.gamelib.AbstractPlayer;

/**
 * Represents a chess player
 */
public final class ChessPlayer extends AbstractPlayer<ChessGame, ChessPlayer> {

	private final ChessColor color;

	/**
	 * Creates a new chess player
	 * 
	 * @param game
	 *            the game this player is in
	 * @param name
	 *            the name of the player
	 * @param color
	 *            the color for this player
	 */
	protected ChessPlayer(final ChessGame game, final String name, final ChessColor color) {
		super(game, name);

		if (color == null)
			throw new IllegalArgumentException("color cannot be null");

		this.color = color;
	}

	/**
	 * Returns the color of this player
	 * 
	 * @return the color of this player
	 */
	public final ChessColor getColor() {
		return color;
	}

	@Override
	public final String toString() {
		return String.format("Chess Player: %s (%s)", getName(), color.toString());
	}

}
