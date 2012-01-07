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
 * Represents a French playing card
 */
public class FrenchCard extends AbstractCard<FrenchCard.FrenchSuit> {
	
	/**
	 * French suits
	 */
	public enum FrenchSuit implements Card.Suit<FrenchSuit> {
		Clubs, Diamonds, Hearts, Spades, Joker
	}
	
	/**
	 * Returns a standard deck of French cards
	 * 
	 * @param addJokers
	 *            set to true to add two Jokers to the deck
	 * @return a standard deck of 52 (+2) French cards
	 */
	public static final CardGroup<FrenchCard> getDeck(final boolean addJokers) {
		final List<FrenchCard> cards = new ArrayList<FrenchCard>(52);
		
		for (final FrenchSuit suit : EnumSet.range(FrenchSuit.Clubs, FrenchSuit.Spades)) {
			for (int i = 1; i <= 13; i++) {
				cards.add(new FrenchCard(suit, i));
			}
		}
		
		if (addJokers) {
			cards.add(new FrenchCard(FrenchSuit.Joker, 1));
			cards.add(new FrenchCard(FrenchSuit.Joker, 2));
		}
		
		return new CardGroup<FrenchCard>(cards);
	}
	
	protected FrenchCard(final FrenchSuit suit, final int rank) {
		super(suit, rank);
	}
	
	@Override protected final int getMaxRank() {
		return 13;
	}
	
	@Override public String toString() {
		if (suit == FrenchSuit.Joker)
			return "Joker";
		
		final String strRank;
		switch (rank) {
		case 1:
			strRank = "Ace";
			break;
		case 11:
			strRank = "Jack";
			break;
		case 12:
			strRank = "Queen";
			break;
		case 13:
			strRank = "King";
			break;
		default:
			strRank = Integer.toString(rank);
		}
		
		return strRank + " of " + suit;
	}
	
}
