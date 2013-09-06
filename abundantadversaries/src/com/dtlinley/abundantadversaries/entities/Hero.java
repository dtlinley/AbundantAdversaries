package com.dtlinley.abundantadversaries.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.dtlinley.abundantadversaries.game.AbundantAdversaries;
import com.dtlinley.abundantadversaries.input.HeroStateMediator;

public class Hero extends Entity {

	private final Sword sword;
	private final HashMap<HeroState, Animation> textures;
	private final HashMap<HeroState, Polygon> shapes;
	private final HeroStateMediator stateController;

	private float stateTime;
	private HeroState state;

	public enum HeroState {
		LEFT, DOWN_LEFT, UP_LEFT, UP, RIGHT, DOWN_RIGHT, UP_RIGHT, NEUTRAL, TRANSITION
	};

	public Hero(Polygon bounds) {
		super(bounds);
		// TODO: real sword polygon
		float[] points = { 0f, 10f, 100f, 10f, 100f, -10f, 0f, -10f };
		Polygon swordPolygon = new Polygon(points);
		sword = new Sword(swordPolygon);
		textures = new HashMap<HeroState, Animation>();
		shapes = new HashMap<HeroState, Polygon>();
		TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("hero.png")));
		textureRegion.setRegionX(0);
		textureRegion.setRegionY(0);
		Animation image = new Animation(100, textureRegion);
		for (HeroState s : HeroState.values()) {
			// TODO: temporarily make all textures and shapes identical to avoid NPE
			// bounds.setOrigin(-500, 500);
			textures.put(s, image);
			shapes.put(s, bounds);
		}
		stateController = new HeroStateMediator(AbundantAdversaries.getInputHandler());
	}

	public void deflect(ArrayList<Enemy> enemies, ArrayList<Projectile> projectiles) {
		for (Projectile p : projectiles) {
			if (sword.collidesWith(p))
				p.deflect();
		}
		for (Enemy e : enemies) {
			if (sword.collidesWith(e))
				e.collide();
		}
	}

	public void checkForDeath(ArrayList<Entity> entities) {
		for (Entity e : entities) {
			if (e.collidesWith(this)) {
				collide();
				return;
			}
		}
	}

	@Override
	public void update(float delta) {
		stateTime += delta;
		HeroState state = stateController.getStateFromInput();
		setState(state);
	}

	private HeroState getState() {
		return state;
	}

	private void setState(HeroState state) {
		if (this.state == state)
			return;

		stateTime = 0;
		this.state = state;
		setShape(shapes.get(state));
		sword.setState(state);
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector3> getRenderables() {
		LinkedHashMap<TextureRegion, Vector3> map = new LinkedHashMap<TextureRegion, Vector3>();
		Vector3 v = new Vector3(getPosition().x, getPosition().y, getRotation());
		map.put(textures.get(getState()).getKeyFrame(stateTime), v);
		map.putAll(sword.getRenderables());
		return map;
	}

	@Override
	public Polygon getShape() {
		return shapes.get(state);
	}

	@Override
	public void setPosition(Vector2 position) {
		for (Polygon shape : shapes.values()) {
			shape.setPosition(position.x, position.y);
		}
	}

	@Override
	public void setPosition(Vector3 position) {
		for (Polygon shape : shapes.values()) {
			shape.setRotation(0);
			shape.setPosition(position.x, position.y);
			shape.setRotation(position.z);
		}
		// setPosition(new Vector2(position.x, position.y));
	}
}
