package com.dtlinley.toomanyninjas.gamestate;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public interface GameState {

	public void update(float delta);

	/**
	 * Used to notify any interested parties (usually just Screens) of which state should be used. Return the current state if no
	 * update is required.
	 * 
	 * @return
	 */
	public GameState getTargetState();

	/**
	 * Game States will never render their own materials. Instead, it is collected by the Screen which coordinates all drawing in
	 * order to gain efficiency.
	 * 
	 * @return
	 */
	public LinkedHashMap<TextureRegion, Vector2> getRenderables();

}
