package com.dtlinley.abundantadversaries.game.maneuvers;

import com.badlogic.gdx.math.Vector2;

/**
 * A Maneuver is a path a mob can follow through the game world. On update, mobs should pass the update delta to the maneuver to
 * get their updated position.
 * 
 * @author david
 * 
 */
public interface Maneuver {
	public Vector2 getNextPosition(float delta);

	public boolean isFinished();
}
