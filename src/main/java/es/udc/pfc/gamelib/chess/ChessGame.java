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

import es.udc.pfc.gamelib.board.BoardGame;
import es.udc.pfc.gamelib.board.InvalidMovementException;
import es.udc.pfc.gamelib.board.Position;

/**
 * Represents a chess game
 */
public interface ChessGame extends BoardGame<ChessBoard, ChessMovement, ChessPlayer> {
	
	/**
	 * Adds a new Player to this chess game
	 * 
	 * @param name
	 *            The name of the new player to add
	 * @return true if the player was added, false otherwise
	 */
	public boolean addPlayer(final String name);
	
	/**
	 * Moves a chess piece
	 * 
	 * @param from
	 *            the source of the movement
	 * @param to
	 *            the destination of the movement
	 * @return true if the movement succeeded, false otherwise
	 */
	public void movePiece(final Position from, final Position to) throws InvalidMovementException;
	
}
