package com.dtlinley.abundantadversaries.game.behaviours;

import com.badlogic.gdx.math.Vector2;
import com.dtlinley.abundantadversaries.game.maneuvers.Maneuver;

/**
 * A Behaviour informs a Mob how to move and how to act
 * 
 * @author david
 * 
 */
public interface Behaviour {
	public Maneuver getNextManeuver(Vector2 mobPos, Vector2 heroPos);

	public boolean shouldShoot(float delta);
}
