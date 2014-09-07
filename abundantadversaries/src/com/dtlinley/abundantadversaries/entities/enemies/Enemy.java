package com.dtlinley.abundantadversaries.entities.enemies;

import java.util.ArrayList;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.dtlinley.abundantadversaries.entities.Mob;
import com.dtlinley.abundantadversaries.entities.Projectile;
import com.dtlinley.abundantadversaries.game.behaviours.Behaviour;

public abstract class Enemy extends Mob {

	protected ArrayList<Projectile> newProjectiles = new ArrayList<Projectile>();
	private final boolean travelingRight;

	protected Enemy(Polygon bounds, Behaviour behaviour, boolean travelingRight) {
		super(bounds, behaviour);
		this.travelingRight = travelingRight;
	}

	protected Vector2 finalDestination() {
		if (travelingRight)
			return new Vector2(300, -100);

		return new Vector2(-300, -100);
	}

	public ArrayList<Projectile> getNewProjectiles() {
		return newProjectiles;
	}
}
