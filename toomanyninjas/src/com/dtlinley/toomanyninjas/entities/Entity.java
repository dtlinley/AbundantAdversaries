package com.dtlinley.toomanyninjas.entities;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	public float velX = 0;
	public float velY = 0;

	protected boolean dead = false;

	private final Polygon shape;

	protected Entity(Polygon shape) {
		this.shape = shape;
	}

	public Polygon getShape() {
		return shape;
	}

	public boolean collidesWith(Entity other) {
		return Intersector.overlapConvexPolygons(getShape(), other.getShape());
	}

	public abstract void collide();

	public boolean isDead() {
		return dead;
	}

	public Vector2 getPosition() {
		return new Vector2(shape.getX(), shape.getY());
	}

	public void setPosition(Vector2 position) {
		shape.setPosition(position.x, position.y);
	}
}
