package com.dtlinley.abundantadversaries.game.maneuvers;

import com.badlogic.gdx.math.Vector2;

public class LinearManeuver implements Maneuver {

	private Vector2 position;
	private final Vector2 velocity;

	public LinearManeuver(Vector2 initialPosition, Vector2 velocity) {
		position = initialPosition;
		this.velocity = velocity;
	}

	@Override
	public Vector2 getNextPosition(float delta) {
		position = position.cpy().add(velocity.cpy().mul(delta));
		return position;
	}

	@Override
	public boolean isFinished() {
		return false;
	}

}
