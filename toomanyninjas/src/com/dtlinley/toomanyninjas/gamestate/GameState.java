package com.dtlinley.toomanyninjas.gamestate;

public interface GameState {

	public void render(float delta);

	public void update(float delta);

	/**
	 * Used to notify any interested parties (usually just Screens) of which state should be used. Return the current state if no
	 * update is required.
	 * 
	 * @return
	 */
	public GameState getTargetState();

}
