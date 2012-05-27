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

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Represents the row and column coordinates of a square in a {@link Board}.
 */
@Immutable
public final class Position implements Comparable<Position> {
	
	private final int column;
	private final int row;
	
	/**
	 * Position constructor.
	 * 
	 * @param column
	 *            the column for the new Position
	 * @param row
	 *            the row for the new position
	 */
	public Position(final int column, final int row) {
		this.column = column;
		this.row = row;
	}
	
	/**
	 * Create a new position from a String.
	 * 
	 * Note: This method only supports positions for up to a 8x8 square board.
	 * 
	 * @param str
	 *            the string to parse
	 * @return a new Position
	 */
	@Nullable public static final Position fromString(final String str) {
		checkArgument(checkNotNull(str).matches("^[a-h][1-8]$"));
		
		return new Position(str.charAt(0) - 'a' + 1, str.charAt(1) - '1' + 1);
	}
	
	/**
	 * Create a new position relative to the this one.
	 * 
	 * @param addColumn
	 *            columns to add/substract to this position
	 * @param addRow
	 *            rows to add/substract from this position
	 * @return the new Position, relative to this one
	 */
	public final Position relative(final int addColumn, final int addRow) {
		return new Position(column + addColumn, row + addRow);
	}
	
	/**
	 * Returns the column for this position.
	 * 
	 * @return the column for this position
	 */
	public final int getColumn() {
		return column;
	}
	
	/**
	 * Returns the row for this position.
	 * 
	 * @return the row for this position
	 */
	public final int getRow() {
		return row;
	}
	
	@Override public final boolean equals(@Nullable final Object obj) {
		if (obj instanceof Position) {
			final Position other = (Position) obj;
			
			return column == other.column && row == other.row;
		}
		
		return false;
	}

	@Override public final int compareTo(@Nullable final Position o) {
		return ComparisonChain.start()
				.compare(column, o.column)
				.compare(row, o.row)
				.result();
	}
	
	@Override public final int hashCode() {
		return Objects.hashCode(Integer.valueOf(column), Integer.valueOf(row));
	}
	
	@Override public final String toString() {
		if (column > 0 && column <= 8 && row > 0 && row <= 8)
			return String.valueOf((char) ('a' + column - 1)) + row;
		
		return "[" + column + "," + row + "]";
	}
	
}
