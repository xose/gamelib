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

import javax.annotation.Nullable;

/**
 * Represents a piece to be used in board games
 */
public interface Piece {
	
	/**
	 * Returns the current {@link Position} of this piece in the {@link Board}
	 * 
	 * @return the current position
	 */
	@Nullable public Position getPosition();
	
	/**
	 * Checks if the given piece is an enemy
	 * 
	 * @param other
	 *            the piece to check
	 * @return true if the other piece is an enemy, false otherwise
	 */
	public boolean isEnemy(final Piece piece);
	
}
