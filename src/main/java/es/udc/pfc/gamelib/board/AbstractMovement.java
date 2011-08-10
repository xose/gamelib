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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Abstract movement class
 * 
 * This class implements the common methods for all {@link Movement} subclasses
 */
public abstract class AbstractMovement implements Movement {
	
	protected final Position from;
	protected final Position to;
	
	protected AbstractMovement(final Position from, final Position to) {
		this.from = checkNotNull(from);
		this.to = checkNotNull(to);
	}
	
	@Override public final Position getFrom() {
		return from;
	}
	
	@Override public final Position getTo() {
		return to;
	}
	
	@Override public String toString() {
		return from.toString() + "-" + to.toString();
	}
}
