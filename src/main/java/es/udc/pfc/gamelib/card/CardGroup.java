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

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.RandomAccess;

/**
 * A group of playing cards
 */
public final class CardGroup<C extends Card<?>> extends LinkedList<C> {
	
	private static final long serialVersionUID = 1L;
	
	public CardGroup() {
		super();
	}
	
	public CardGroup(final List<C> cards) {
		super(cards);
	}
	
	/**
	 * Shuffles the cards in this group
	 */
	public final void shuffle() {
		shuffle(this, new Random());
	}
	
	/**
	 * Moves every element of the List to a random new position in the list
	 * using the specified random number generator.
	 * 
	 * @param list
	 *            the List to shuffle
	 * @param random
	 *            the random number generator
	 * 
	 * @throws UnsupportedOperationException
	 *             when replacing an element in the List is not supported
	 */
	@SuppressWarnings("unchecked") private static final void shuffle(final List<?> list, final Random random) {
		/* Code from Apache Harmony */
		
		if (!(list instanceof RandomAccess)) {
			final Object[] array = list.toArray();
			for (int i = array.length - 1; i > 0; i--) {
				int index = random.nextInt(i + 1);
				if (index < 0) {
					index = -index;
				}
				final Object temp = array[i];
				array[i] = array[index];
				array[index] = temp;
			}
			
			int i = 0;
			final ListIterator<Object> it = (ListIterator<Object>) list.listIterator();
			while (it.hasNext()) {
				it.next();
				it.set(array[i++]);
			}
		} else {
			final List<Object> rawList = (List<Object>) list;
			for (int i = rawList.size() - 1; i > 0; i--) {
				int index = random.nextInt(i + 1);
				if (index < 0) {
					index = -index;
				}
				rawList.set(index, rawList.set(i, rawList.get(index)));
			}
		}
	}
	
}
