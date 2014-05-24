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
import com.dtlinley.abundantadversaries.input.HeroStateController;

public class Sword extends Entity {

	private final HashMap<HeroState, Animation> textures;
	private final HashMap<HeroState, Polygon> shapes;
	private final HashMap<HeroState, Vector3> swordPositions;
	private final HeroStateController stateController;

	private Vector2 basePosition = new Vector2();

	protected Sword(Polygon shape, HashMap<HeroState, Vector3> swordPositions, HeroStateController stateController) {
		super(shape);
		textures = new HashMap<HeroState, Animation>();
		shapes = new HashMap<HeroState, Polygon>();
		this.swordPositions = swordPositions;
		this.stateController = stateController;

		Texture texture = new Texture(Gdx.files.internal("sword.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Animation image = new Animation(100, new TextureRegion(texture));
		for (HeroState s : HeroState.values()) {
			// TODO: temporarily make all textures and shapes identical to avoid NPE
			textures.put(s, image);
			shapes.put(s, shape);
		}
	}

	@Override
	public void collide() {
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public LinkedHashMap<TextureRegion, Polygon> getRenderables() {
		LinkedHashMap<TextureRegion, Polygon> map = new LinkedHashMap<TextureRegion, Polygon>();
		Polygon p = getShape();
		map.put(textures.get(stateController.getState()).getKeyFrame(stateController.getStateTime()), p);
		return map;
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
		Vector3 swordPos = swordPositions.get(stateController.getState());
		return basePosition.cpy().add(swordPos.x, swordPos.y);
	}

	@Override
	public float getRotation() {
		return super.getRotation() + swordPositions.get(stateController.getState()).z;
	}

	@Override
	public Polygon getShape() {
		Polygon shape = shapes.get(stateController.getState());
		Vector3 v = swordPositions.get(stateController.getState());
		shape.setPosition(basePosition.x + v.x, basePosition.y + v.y);
		shape.setRotation(v.z);
		return shape;
	}
}
