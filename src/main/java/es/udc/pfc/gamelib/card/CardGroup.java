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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A group of playing cards
 */
public final class CardGroup<C extends Card<? extends Card.Suit>> extends LinkedList<C> {

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
		Collections.shuffle(this);
	}

}
