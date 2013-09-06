package com.dtlinley.abundantadversaries.gamestate;

import com.dtlinley.abundantadversaries.game.Renderable;

public interface GameState extends Renderable {

	public void update(float delta);

	public void render();

	/**
	 * Used to notify any interested parties (usually just Screens) of which state should be used. Return the current state if no
	 * update is required.
	 * 
	 * @return
	 */
	public GameState getTargetState();

}
