package com.dtlinley.abundantadversaries.entities;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class BallisticProjectile extends Projectile {

	private final Vector2 gravity = new Vector2(0, -0.0001f);

	public BallisticProjectile(Polygon bounds) {
		super(bounds);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		setVelocity(getVelocity().add(gravity.mul(delta)));
	}

}
