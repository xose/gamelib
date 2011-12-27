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

public class MovementTest {
	
	@Test public void testConstructor() {
		final SimpleMovement p = new SimpleMovement(new Position(3, 4), new Position(3, 6));
		
		assertEquals(new Position(3, 4), p.getFrom());
		assertEquals(new Position(3, 6), p.getTo());
	}
	
	@Test public void testEquality() {
		final SimpleMovement p1 = new SimpleMovement(new Position(3, 4), new Position(3, 6));
		final SimpleMovement p2 = new SimpleMovement(new Position(3, 4), new Position(3, 6));
		final SimpleMovement p3 = new SimpleMovement(new Position(2, 7), new Position(2, 5));
		
		assertNotSame(p1, p2);
		
		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(p3));
	}
	
	@Test public void testHashCode() {
		final SimpleMovement p1 = new SimpleMovement(new Position(3, 4), new Position(3, 6));
		final SimpleMovement p2 = new SimpleMovement(new Position(3, 4), new Position(3, 6));
		final SimpleMovement p3 = new SimpleMovement(new Position(2, 7), new Position(2, 5));
		
		assertNotSame(p1, p2);
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println(p3.hashCode());
		assertTrue(p1.hashCode() == p2.hashCode());
		assertFalse(p1.hashCode() == p3.hashCode());
		assertFalse(p2.hashCode() == p3.hashCode());
	}
	
	@Test public void testToString() {
		assertEquals("c4-c6", new SimpleMovement(new Position(3, 4), new Position(3, 6)).toString());
	}
	
}
