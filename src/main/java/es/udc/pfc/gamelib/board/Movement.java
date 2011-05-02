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

/**
 * Represents a {@link Piece} movement in a {@link Board}
 */
public interface Movement<B extends Board<B, M, P>, M extends Movement<B, M, P>, P extends Piece<B, M, P>> {

	/**
	 * Returns the piece involved in the movement
	 * 
	 * @return the piece for this movement
	 */
	public P getPiece();

	/**
	 * Returns the source of this movement
	 * 
	 * @return the source position for this movement
	 */
	public Position getFrom();

	/**
	 * Returns the destination of this movement
	 * 
	 * @return the source position for this movement
	 */
	public Position getTo();

}
