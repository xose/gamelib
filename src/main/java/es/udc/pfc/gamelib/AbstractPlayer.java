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

package es.udc.pfc.gamelib;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Abstract player class
 * 
 * This class implements the common methods for all {@link Player} subclasses
 */
public abstract class AbstractPlayer implements Player {
	
	protected final Game<?> game;
	protected final String name;
	
	protected AbstractPlayer(final Game<?> game, final String name) {
		this.game = checkNotNull(game);
		this.name = checkNotNull(name);
	}
	
	@Override public final String getName() {
		return name;
	}
	
	@Override public final boolean isCurrentTurn() {
		return equals(game.getCurrentPlayer());
	}
	
	@Override public abstract String toString();
	
}
