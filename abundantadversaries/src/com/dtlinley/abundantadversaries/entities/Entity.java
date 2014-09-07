package com.dtlinley.abundantadversaries.entities;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.dtlinley.abundantadversaries.game.Renderable;

public abstract class Entity implements Renderable {

	protected boolean dead = false;

	private Polygon shape;

	protected Entity(Polygon shape) {
		this.shape = shape;
	}

	public void collide() {
		dead = true;
	}

	public boolean collidesWith(Entity other) {
		return Intersector.overlapConvexPolygons(getShape(), other.getShape());
	}

	public Vector2 getPosition() {
		return new Vector2(shape.getX(), shape.getY());
	}

	public float getRotation() {
		return shape.getRotation();
	}

	public Polygon getShape() {
		return shape;
	}

	public boolean isDead() {
		return dead;
	}

	public void setPosition(Vector2 position) {
		shape.setPosition(position.x, position.y);
	}

	public void setPosition(Vector3 position) {
		shape.setRotation(position.z);
		setPosition(new Vector2(position.x, position.y));
	}

	protected void setShape(Polygon shape) {
		this.shape = shape;
	}

	public abstract void update(float delta);
}
