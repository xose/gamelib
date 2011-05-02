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

import java.util.Set;

/**
 * Represents a piece to be used in board games
 */
public interface Piece<B extends Board<B, M, P>, M extends Movement<B, M, P>, P extends Piece<B, M, P>> {

	/**
	 * Returns the current {@link Position} of this piece in the {@link Board}
	 * 
	 * @return the current position
	 */
	public Position getPosition();

	/**
	 * Checks if the piece can move to the given position
	 * 
	 * @param to
	 *            position to move the piece
	 * @return true if the piece can move, otherwise false
	 */
	public boolean canMove(final Position to);

	/**
	 * Returns a set of all possible movements for this piece
	 * 
	 * @return an unmodifiable set of movements for this piece
	 */
	public Set<Position> getAllMoves();

	/**
	 * Returns the {@link Board} this piece belongs to
	 * 
	 * @return the board this piece belongs to
	 */
	public B getBoard();

	/**
	 * Checks if the given piece is an enemy
	 * 
	 * @param other
	 *            the piece to check
	 * @return true if the other piece is an enemy, false otherwise
	 */
	public boolean isEnemy(final P other);

}
