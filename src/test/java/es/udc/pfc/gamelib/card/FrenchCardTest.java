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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FrenchCardTest {

	@Test
	public void testConstructor() {
		final FrenchCard c = new FrenchCard(FrenchCard.FrenchSuit.Diamonds, 5);

		assertEquals(FrenchCard.FrenchSuit.Diamonds, c.getSuit());
		assertEquals(5, c.getRank());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorRange() {
		new FrenchCard(FrenchCard.FrenchSuit.Clubs, 14);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegative() {
		new FrenchCard(FrenchCard.FrenchSuit.Hearts, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNull() {
		new FrenchCard(null, 5);
	}

	@Test
	public void testDecks() {
		assertEquals(52, FrenchCard.getDeck(false).size());
		assertEquals(54, FrenchCard.getDeck(true).size());
	}

	@Test
	public void testEquals() {
		assertEquals(new FrenchCard(FrenchCard.FrenchSuit.Hearts, 3), new FrenchCard(FrenchCard.FrenchSuit.Hearts, 3));
	}

	@Test
	public void testToString() {
		assertEquals("3 of Spades", new FrenchCard(FrenchCard.FrenchSuit.Spades, 3).toString());
		assertEquals("7 of Clubs", new FrenchCard(FrenchCard.FrenchSuit.Clubs, 7).toString());

		assertEquals("Joker", new FrenchCard(FrenchCard.FrenchSuit.Joker, 1).toString());
		assertEquals("Ace of Diamonds", new FrenchCard(FrenchCard.FrenchSuit.Diamonds, 1).toString());
		assertEquals("Jack of Clubs", new FrenchCard(FrenchCard.FrenchSuit.Clubs, 11).toString());
		assertEquals("Queen of Hearts", new FrenchCard(FrenchCard.FrenchSuit.Hearts, 12).toString());
		assertEquals("King of Spades", new FrenchCard(FrenchCard.FrenchSuit.Spades, 13).toString());
	}
}
