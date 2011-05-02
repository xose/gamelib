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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract piece class
 * 
 * This class implements the common methods for all {@link Piece} subclasses
 */
public abstract class AbstractPiece<B extends Board<B, M, P>, M extends Movement<B, M, P>, P extends Piece<B, M, P>> implements Piece<B, M, P> {

	private final B board;

	/**
	 * Represents the directions that most pieces use to move
	 */
	protected enum Direction {
		/** North */
		N(0, 1),
		/** South */
		S(0, -1),
		/** East */
		E(1, 0),
		/** West */
		W(-1, 0),
		/** North-East */
		NE(1, 1),
		/** North-West */
		NW(-1, 1),
		/** South-East */
		SE(1, -1),
		/** South-West */
		SW(-1, -1);

		private final int i;
		private final int j;

		private Direction(final int i, final int j) {
			this.i = i;
			this.j = j;
		}

		public final int i() {
			return i;
		}

		public final int j() {
			return j;
		}
	}

	protected AbstractPiece(final B board) {
		if (board == null)
			throw new IllegalArgumentException("board cannot be null");

		this.board = board;
	}

	@Override
	public final B getBoard() {
		return this.board;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final Position getPosition() {
		return board.getPositionFor((P) this);
	}

	/**
	 * Returns all the possible moves to a given direction
	 * 
	 * @param dir
	 *            the direction to get the piece moves
	 * @return a set of positions the piece can move to
	 */
	protected final Set<Position> getMovesTo(final Direction dir) {
		final HashSet<Position> moves = new HashSet<Position>();

		Position pos = getPosition().relative(dir.i(), dir.j());
		while (board.isValidPosition(pos) && (!board.isPieceAt(pos) || isEnemy(getBoard().getPieceAt(pos)))) {
			moves.add(pos);

			if (board.isPieceAt(pos)) {
				break;
			}

			pos = pos.relative(dir.i(), dir.j());
		}

		return Collections.unmodifiableSet(moves);
	}

	@Override
	public boolean canMove(final Position to) {
		return getAllMoves().contains(to);
	}

	@Override
	public abstract String toString();

}
