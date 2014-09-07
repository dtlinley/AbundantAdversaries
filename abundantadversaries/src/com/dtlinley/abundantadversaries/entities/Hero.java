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
import com.dtlinley.abundantadversaries.entities.enemies.Enemy;
import com.dtlinley.abundantadversaries.input.HeroStateController;
import com.dtlinley.abundantadversaries.input.UpdateableHeroStateController;

public class Hero extends Entity {

	public enum HeroState {
		LEFT, DOWN_LEFT, UP_LEFT, UP, RIGHT, DOWN_RIGHT, UP_RIGHT, NEUTRAL, TRANSITION
	}

	public static Vector2 getHeroPosition() {
		return new Vector2(0, -68);
	}

	private final Sword sword;
	private final HashMap<HeroState, Animation> textures;

	private final HashMap<HeroState, Polygon> shapes;

	private final UpdateableHeroStateController stateController;

	public Hero(Polygon bounds) {
		super(bounds);
		// TODO: real sword polygon
		Polygon swordPolygon = new Polygon(new float[] { 0f, 20f, 70f, 20f, 70f, 0f });
		swordPolygon.setOrigin(10, 10);
		textures = new HashMap<HeroState, Animation>();
		shapes = new HashMap<HeroState, Polygon>();
		TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("hero.png")));
		textureRegion.setRegionX(0);
		textureRegion.setRegionY(0);
		Animation image = new Animation(100, textureRegion);
		for (HeroState s : HeroState.values()) {
			// TODO: temporarily make all textures and shapes identical to avoid NPE
			textures.put(s, image);
			shapes.put(s, bounds);
		}

		stateController = new UpdateableHeroStateController();

		sword = new Sword(swordPolygon, swordPositions(), getStateController());
	}

	public void checkForDeath(ArrayList<Entity> entities) {
		for (Entity e : entities) {
			if (e.collidesWith(this)) {
				collide();
				return;
			}
		}
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

	@Override
	public LinkedHashMap<TextureRegion, Polygon> getRenderables() {
		LinkedHashMap<TextureRegion, Polygon> map = new LinkedHashMap<TextureRegion, Polygon>();
		Polygon p = getShape();
		map.put(textures.get(stateController.getState()).getKeyFrame(stateController.getStateTime()), p);
		map.putAll(sword.getRenderables());
		return map;
	}

	@Override
	public Polygon getShape() {
		return shapes.get(stateController.getState());
	}

	private HeroStateController getStateController() {
		return stateController;
	}

	@Override
	public void setPosition(Vector2 position) {
		for (Polygon shape : shapes.values()) {
			shape.setPosition(position.x, position.y);
		}
		sword.setPosition(position);
	}

	@Override
	public void setPosition(Vector3 position) {
		for (Polygon shape : shapes.values()) {
			shape.setRotation(position.z);
		}
		setPosition(new Vector2(position.x, position.y));
	}

	private HashMap<HeroState, Vector3> swordPositions() {
		HashMap<HeroState, Vector3> swordPositions = new HashMap<HeroState, Vector3>();
		swordPositions.put(HeroState.TRANSITION, new Vector3(0, 15, 0));
		swordPositions.put(HeroState.NEUTRAL, new Vector3(25, 10, -10f));
		swordPositions.put(HeroState.DOWN_LEFT, new Vector3(15, 10, 200f));
		swordPositions.put(HeroState.LEFT, new Vector3(10, 15, 180f));
		swordPositions.put(HeroState.UP_LEFT, new Vector3(10, 25, 150f));
		swordPositions.put(HeroState.UP, new Vector3(0, 30, 25f));
		swordPositions.put(HeroState.UP_RIGHT, new Vector3(40, 30, 30f));
		swordPositions.put(HeroState.RIGHT, new Vector3(40, 20, 0f));
		swordPositions.put(HeroState.DOWN_RIGHT, new Vector3(40, 10, -20f));
		return swordPositions;
	}

	@Override
	public void update(float delta) {
		stateController.update(delta);
	}
}
