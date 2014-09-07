package com.dtlinley.abundantadversaries.game.behaviours;

import com.badlogic.gdx.math.Vector2;
import com.dtlinley.abundantadversaries.game.maneuvers.LinearManeuver;
import com.dtlinley.abundantadversaries.game.maneuvers.Maneuver;

public class LinearMovement implements Behaviour {

	private final float speed;

	public LinearMovement(float speed) {
		this.speed = speed;
	}

	@Override
	public Maneuver getNextManeuver(Vector2 mobPos, Vector2 heroPos) {
		Vector2 direction = heroPos.cpy().sub(mobPos.cpy()).nor();
		return new LinearManeuver(mobPos.cpy(), direction.mul(speed));
	}

	@Override
	public boolean shouldShoot(float delta) {
		return false;
	}

}
