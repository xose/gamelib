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

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Represents a Spanish playing card
 */
public class SpanishCard extends AbstractCard<SpanishCard.SpanishSuit> {

	/**
	 * Spanish suits
	 */
	public enum SpanishSuit implements Card.Suit {
		Oros, Copas, Espadas, Bastos, Comodin
	}

	/**
	 * Returns a standard deck of Spanish cards
	 * 
	 * @param extra
	 *            set to true to add cards of ranks 8 and 9
	 * @param addJokers
	 *            set to true to add two Jokers to the deck
	 * @return a standard deck of 40 (+8) (+2) Spanish cards
	 */
	public static final CardGroup<SpanishCard> getDeck(final boolean extra, final boolean addJokers) {
		final List<SpanishCard> cards = new ArrayList<SpanishCard>(40);

		for (final SpanishSuit suit : EnumSet.range(SpanishSuit.Oros, SpanishSuit.Bastos)) {
			for (int i = 1; i <= 12; i++) {
				if (!extra && (i == 8 || i == 9)) {
					continue;
				}

				cards.add(new SpanishCard(suit, i));
			}
		}

		if (addJokers) {
			cards.add(new SpanishCard(SpanishSuit.Comodin, 1));
			cards.add(new SpanishCard(SpanishSuit.Comodin, 2));
		}

		return new CardGroup<SpanishCard>(cards);
	}

	protected SpanishCard(final SpanishSuit suit, final int rank) {
		super(suit, rank);
	}

	@Override
	protected int getMaxRank() {
		return 12;
	}

	@Override
	public String toString() {
		if (getSuit() == SpanishSuit.Comodin)
			return "Comodín";

		final String strRank;
		switch (getRank()) {
		case 1:
			strRank = "As";
			break;
		case 10:
			strRank = "Sota";
			break;
		case 11:
			strRank = "Caballo";
			break;
		case 12:
			strRank = "Rey";
			break;
		default:
			strRank = Integer.toString(getRank());
		}

		return String.format("%s de %s", strRank, getSuit());
	}

}
