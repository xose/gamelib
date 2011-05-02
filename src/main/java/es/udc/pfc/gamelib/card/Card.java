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

/**
 * Represents a playing card
 */
public interface Card<S extends Card.Suit> {

	public interface Suit {
	}

	/**
	 * Returns the rank of this card
	 * 
	 * @return the rank of this card
	 */
	int getRank();

	/**
	 * Returns a String representing the suit of this card
	 * 
	 * @return the suit of this card
	 */
	S getSuit();

}
