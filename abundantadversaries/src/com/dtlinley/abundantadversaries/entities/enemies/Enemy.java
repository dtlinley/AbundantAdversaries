package com.dtlinley.abundantadversaries.entities.enemies;

import java.util.ArrayList;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.dtlinley.abundantadversaries.entities.Mob;
import com.dtlinley.abundantadversaries.entities.Projectile;

public abstract class Enemy extends Mob {

	protected ArrayList<Projectile> newProjectiles = new ArrayList<Projectile>();
	private final boolean travelingRight;

	protected Enemy(Polygon bounds, boolean travelingRight) {
		super(bounds);
		this.travelingRight = travelingRight;
	}

	public ArrayList<Projectile> getNewProjectiles() {
		return newProjectiles;
	}
	
	protected Vector2 finalDestination() {
		if (this.travelingRight)
			return new Vector2(300, -100);
		
		return new Vector2(-300, -100);
	}
}
