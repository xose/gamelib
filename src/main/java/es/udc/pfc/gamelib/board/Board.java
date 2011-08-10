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

import java.util.Collection;

import javax.annotation.Nullable;

/**
 * Represents a square gaming board
 */
public interface Board<P extends Piece> {
	
	/**
	 * Returns the number of columns in this board
	 * 
	 * @return the number of columns in this board
	 */
	public int getNumberOfColumns();
	
	/**
	 * Returns the number of rows in this board
	 * 
	 * @return the number of rows in this board
	 */
	public int getNumberOfRows();
	
	/**
	 * Checks if {@code position} is a valid board position
	 * 
	 * @param position
	 *            the {@link Position} to test
	 * @return true if the position is valid, false otherwise
	 */
	public boolean isValidPosition(final Position position);
	
	/**
	 * Checks if there is a piece in {@code position}
	 * 
	 * @param position
	 *            the {@link Position} to test
	 * @return true if there is a piece, false otherwise
	 */
	public boolean isPieceAt(final Position position);
	
	/**
	 * Gets the piece in {@code position}
	 * 
	 * @param position
	 *            the {@link Position} to get the piece
	 * @return the piece at the given position, {@code null} if there is no
	 *         piece
	 */
	@Nullable public P getPieceAt(final Position position);
	
	/**
	 * Sets the piece in {@code position} to a new piece
	 * 
	 * @param position
	 *            the {@link Position} to get the piece
	 * @param piece
	 *            the Piece to set at the position, null to remove
	 * @return the piece that was at the given position, {@code null} if there
	 *         was no piece
	 */
	@Nullable public P setPieceAt(final Position position, final P piece);
	
	/**
	 * Returns all the pieces for this board
	 * 
	 * @return an unmodifiable collection of all the pieces in the board
	 */
	public Collection<P> getAllPieces();
	
}
