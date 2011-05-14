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

public class SpanishCardTest {

	@Test
	public void testConstructor() {
		final SpanishCard c = new SpanishCard(SpanishCard.SpanishSuit.Copas, 5);

		assertEquals(SpanishCard.SpanishSuit.Copas, c.getSuit());
		assertEquals(5, c.getRank());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorRange() {
		new SpanishCard(SpanishCard.SpanishSuit.Oros, 14);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegative() {
		new SpanishCard(SpanishCard.SpanishSuit.Espadas, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNull() {
		new SpanishCard(null, 5);
	}

	@Test
	public void testDecks() {
		assertEquals(40, SpanishCard.getDeck(false, false).size());
		assertEquals(42, SpanishCard.getDeck(false, true).size());
		assertEquals(48, SpanishCard.getDeck(true, false).size());
		assertEquals(50, SpanishCard.getDeck(true, true).size());
	}

	@Test
	public void testToString() {
		assertEquals("3 de Espadas", new SpanishCard(SpanishCard.SpanishSuit.Espadas, 3).toString());
		assertEquals("7 de Copas", new SpanishCard(SpanishCard.SpanishSuit.Copas, 7).toString());

		assertEquals("Comodín", new SpanishCard(SpanishCard.SpanishSuit.Comodin, 1).toString());
		assertEquals("As de Oros", new SpanishCard(SpanishCard.SpanishSuit.Oros, 1).toString());
		assertEquals("Sota de Copas", new SpanishCard(SpanishCard.SpanishSuit.Copas, 10).toString());
		assertEquals("Caballo de Espadas", new SpanishCard(SpanishCard.SpanishSuit.Espadas, 11).toString());
		assertEquals("Rey de Bastos", new SpanishCard(SpanishCard.SpanishSuit.Bastos, 12).toString());
	}
}
