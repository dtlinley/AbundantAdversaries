package abundantadversaries.entities;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public abstract class Entity {

	private Vector2 vel = new Vector2();

	protected boolean dead = false;

	private Polygon shape;

	protected Entity(Polygon shape) {
		this.shape = shape;
	}

	public Polygon getShape() {
		return shape;
	}

	protected void setShape(Polygon shape) {
		this.shape = shape;
	}

	public boolean collidesWith(Entity other) {
		return Intersector.overlapConvexPolygons(getShape(), other.getShape());
	}

	public void collide() {
		this.dead = true;
	}

	public boolean isDead() {
		return dead;
	}

	public Vector2 getPosition() {
		return new Vector2(shape.getX(), shape.getY());
	}

	public float getRotation() {
		return shape.getRotation();
	}

	public void setPosition(Vector2 position) {
		shape.setPosition(position.x, position.y);
	}

	public void setPosition(Vector3 position) {
		shape.setPosition(position.x, position.y);
		shape.setRotation(position.z);
	}

	public Vector2 getVelocity() {
		return vel.cpy();
	}

	public void setVelocity(Vector2 vel) {
		this.vel = vel;
	}

	public void update(float delta) {
		setPosition(getVelocity().mul(delta).add(getPosition()));
	}

	public abstract LinkedHashMap<TextureRegion, Vector2> getRenderables();
}
