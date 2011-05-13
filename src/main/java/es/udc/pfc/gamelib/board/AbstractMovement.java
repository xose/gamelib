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
 * Abstract movement class
 * 
 * This class implements the common methods for all {@link Movement} subclasses
 */
public abstract class AbstractMovement<B extends Board<B, M, P>, M extends Movement<B, M, P>, P extends Piece<B, M, P>> implements Movement<B, M, P> {

	private final P piece;
	private final Position from;
	private final Position to;

	protected AbstractMovement(final P piece, final Position from, final Position to) {
		if (piece == null)
			throw new IllegalArgumentException("piece cannot be null");
		if (from == null)
			throw new IllegalArgumentException("from cannot be null");
		if (to == null)
			throw new IllegalArgumentException("to cannot be null");

		this.piece = piece;
		this.from = from;
		this.to = to;
	}

	@Override
	public final P getPiece() {
		return piece;
	}

	@Override
	public final Position getFrom() {
		return from;
	}

	@Override
	public final Position getTo() {
		return to;
	}

	@Override
	public String toString() {
		return getFrom().toString() + " " + getTo().toString();
	}
}
