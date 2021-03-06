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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;

/**
 * Abstract board class.
 * 
 * This class implements the common methods for all {@link Board} subclasses.
 * 
 * @param <P> the piece type for this board
 */
public abstract class AbstractBoard<P extends Piece> implements Board<P> {
	
	protected final Map<Position, P> pieces;
	protected final int rows, cols;
	
	protected AbstractBoard(final int rows, final int cols) {
		checkArgument(rows > 0);
		checkArgument(cols > 0);
		
		this.pieces = Maps.newHashMap();
		this.rows = rows;
		this.cols = cols;
	}
	
	@Override public final int getNumberOfRows() {
		return rows;
	}

	@Override public final int getNumberOfColumns() {
		return cols;
	}
	
	/**
	 * Checks if the given position is inside the board bounds.
	 * 
	 * @param position
	 *            the {@link Position} to test
	 * @return true if the position is in bounds, false otherwise
	 */
	protected final boolean isPositionInBounds(final Position position) {
		checkNotNull(position);
		
		final int col = position.getColumn();
		final int row = position.getRow();
		
		return col > 0 && col <= cols && row > 0 && row <= rows;
	}
	
	@Override public boolean isValidPosition(final Position position) {
		return isPositionInBounds(position);
	}
	
	@Override public final boolean isPieceAt(final Position position) {
		checkArgument(isValidPosition(position));
		
		return pieces.get(position) != null;
	}
	
	@Override public final P getPieceAt(final Position position) {
		checkArgument(isValidPosition(position));
		
		return pieces.get(position);
	}
	
	@Override public final P setPieceAt(final Position position, @Nullable final P piece) {
		checkArgument(isValidPosition(position));
		
		if (piece == null)
			return pieces.remove(position);
		
		return pieces.put(position, piece);
	}
	
	@Override public final boolean containsPiece(final Piece piece) {
		return pieces.containsValue(checkNotNull(piece));
	}
	
	@Override public final Position getPositionFor(final Piece piece) {
		checkNotNull(piece);
		
		for (final Map.Entry<Position, P> entry : pieces.entrySet()) {
			if (piece == entry.getValue())
				return entry.getKey();
		}
		
		throw new IllegalArgumentException("Piece is not contained on this board");
	}
	
	@Override public final Collection<P> getAllPieces() {
		return Collections.unmodifiableCollection(pieces.values());
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(getClass(), Integer.valueOf(rows), Integer.valueOf(cols), pieces);
	}
	
	@Override
	public boolean equals(@Nullable final Object obj) {
		if (obj instanceof AbstractBoard) {
			final AbstractBoard<?> other = (AbstractBoard<?>) obj;
			
			return getClass() == other.getClass() && rows == other.rows && cols == other.cols && pieces.equals(other.pieces);
		}
		
		return false;
	}
	
	@Override public final String toString() {
		final StringBuilder fen = new StringBuilder();
		
		for (int row = rows; row >= 1; row--) {
			int empty = 0;
			
			for (int col = 1; col <= cols; col++) {
				final P piece = pieces.get(new Position(col, row));
				if (piece == null) {
					empty++;
					continue;
				}
				
				if (empty > 0) {
					fen.append(empty);
					empty = 0;
				}
				
				fen.append(piece.toString());
			}
			
			if (empty > 0) {
				fen.append(empty);
			}
			
			if (row > 1) {
				fen.append('/');
			}
		}
		
		return fen.toString();
	}
}
