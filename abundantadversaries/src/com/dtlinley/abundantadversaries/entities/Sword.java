package com.dtlinley.abundantadversaries.entities;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.dtlinley.abundantadversaries.entities.Hero.HeroState;

public class Sword extends Entity {

	private final HashMap<HeroState, Animation> textures;
	private final HashMap<HeroState, Polygon> shapes;
	private final HashMap<HeroState, Vector3> swordPositions;

	private HeroState state;
	private float stateTime;
	private Vector2 basePosition = new Vector2();

	protected Sword(Polygon shape, HashMap<HeroState, Vector3> swordPositions) {
		super(shape);
		textures = new HashMap<HeroState, Animation>();
		shapes = new HashMap<HeroState, Polygon>();
		this.swordPositions = swordPositions;
		Texture texture = new Texture(Gdx.files.internal("sword.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Animation image = new Animation(100, new TextureRegion(texture));
		for (HeroState s : HeroState.values()) {
			// TODO: temporarily make all textures and shapes identical to avoid NPE
			textures.put(s, image);
			shapes.put(s, shape);
		}

		setState(HeroState.NEUTRAL);
	}

	@Override
	public void collide() {
	}

	@Override
	public void update(float delta) {
		this.stateTime += delta;
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector3> getRenderables() {
		LinkedHashMap<TextureRegion, Vector3> map = new LinkedHashMap<TextureRegion, Vector3>();
		Vector3 v = new Vector3(getPosition().x, getPosition().y, getRotation());
		map.put(textures.get(getState()).getKeyFrame(stateTime), v);
		return map;
	}

	public HeroState getState() {
		return state;
	}

	public void setState(HeroState state) {
		if (this.state == state)
			return;

		stateTime = 0;

		this.state = state;
		setShape(shapes.get(state));
		this.setPosition(new Vector3(basePosition.x, basePosition.y, 0));
	}

	@Override
	public void setPosition(Vector2 position) {
		basePosition = position;
		super.setPosition(position);
	}

	@Override
	public void setPosition(Vector3 position) {
		basePosition = new Vector2(position.x, position.y);
		super.setPosition(position);
	}

	@Override
	public Vector2 getPosition() {
		Vector3 swordPos = swordPositions.get(state);
		return basePosition.cpy().add(swordPos.x, swordPos.y);
	}

	@Override
	public float getRotation() {
		return super.getRotation() + swordPositions.get(state).z;
	}
}
