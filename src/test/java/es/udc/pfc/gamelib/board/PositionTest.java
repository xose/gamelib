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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testConstructor() {
		final Position p = new Position(3, 4);

		assertEquals(3, p.getColumn());
		assertEquals(4, p.getRow());
	}

	@Test
	public void testEquality() {
		final Position p1 = new Position(3, 6);
		final Position p2 = new Position(3, 6);
		final Position p3 = new Position(2, 7);

		assertNotSame(p1, p2);

		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(p3));
	}

	@Test
	public void testFromString() {
		assertEquals(new Position(3, 5), Position.fromString("c5"));
	}

	@Test
	public void testToString() {
		assertEquals("e2", new Position(5, 2).toString());
	}

	@Test
	public void testRelative() {
		final Position origin = new Position(6, 3);

		assertEquals(new Position(8, 6), origin.relative(2, 3));
		assertEquals(new Position(3, 1), origin.relative(-3, -2));
	}

}
