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

package es.udc.pfc.gamelib.card;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;

/**
 * Abstract card class
 * 
 * This class implements the common methods for all {@link Card} subclasses
 */
public abstract class AbstractCard<S extends Card.Suit> implements Card<S> {
	
	protected final S suit;
	protected final int rank;
	
	protected AbstractCard(final S suit, final int rank) {
		this.suit = checkNotNull(suit);
		this.rank = rank;
		
		checkArgument(rank > 0 && rank <= getMaxRank());
	}
	
	/**
	 * Returns the maximum possible rank for this kind of cards
	 * 
	 * @return the max rank for the card
	 */
	protected abstract int getMaxRank();
	
	@Override public final S getSuit() {
		return suit;
	}
	
	@Override public final int getRank() {
		return rank;
	}
	
	@Override public int hashCode() {
		return Objects.hashCode(suit, Integer.valueOf(rank));
	}
	
	@Override public boolean equals(final Object obj) {
		if (getClass() == obj.getClass()) {
			final AbstractCard<?> other = (AbstractCard<?>) obj;
			
			return this.suit == other.suit && this.rank == other.rank;
		}
		
		return super.equals(obj);
	}
	
	@Override public abstract String toString();
	
}
