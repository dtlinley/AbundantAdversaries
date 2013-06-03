package com.dtlinley.toomanyninjas.entities;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	public float velX = 0;
	public float velY = 0;

	protected boolean dead = false;

	private final Polygon bounds;

	protected Entity(Polygon bounds) {
		this.bounds = bounds;
	}

	public Polygon getBounds() {
		return bounds;
	}

	public boolean collidesWith(Entity other) {
		// TODO: implement collision checking
		return false;
	}

	public abstract void collide();

	public boolean isDead() {
		return dead;
	}

	public Vector2 getPosition() {
		return new Vector2(bounds.getX(), bounds.getY());
	}

	public void setPosition(Vector2 position) {
		bounds.setPosition(position.x, position.y);
	}
}
