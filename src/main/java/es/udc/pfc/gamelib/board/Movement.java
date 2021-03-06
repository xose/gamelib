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

/**
 * Represents a movement in a {@link Board}.
 */
public interface Movement {
	
	/**
	 * Returns the source of this movement.
	 * 
	 * @return the source position for this movement
	 */
	Position getFrom();
	
	/**
	 * Returns the destination of this movement.
	 * 
	 * @return the destination position for this movement
	 */
	Position getTo();
	
}
