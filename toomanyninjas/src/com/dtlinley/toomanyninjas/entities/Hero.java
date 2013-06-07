package com.dtlinley.toomanyninjas.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Hero extends Entity {

	private Sword sword;
	private HashMap<HeroState, Animation> textures;
	private HashMap<HeroState, Polygon> shapes;
	private HashMap<HeroState, Vector2> swordPositions;
	private float stateTime;
	private HeroState state;

	protected enum HeroState {
		LEFT, DOWN_LEFT, UP_LEFT, UP, RIGHT, DOWN_RIGHT, UP_RIGHT
	};

	public Hero(Polygon bounds) {
		super(bounds);
		sword = new Sword(null);
	}

	public void deflect(ArrayList<Entity> entities) {
		// TODO: scan through list of entities, find those that collide with the sword, do damage to them
	}

	public void checkForDeath(ArrayList<Entity> entities) {
		// TODO: scan list of entities, find those colliding with self-- if one found, die
	}

	@Override
	public void collide() {
		dead = true;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	private HeroState getState() {
		return state;
	}

	private void setState(HeroState state) {
		if (this.state != state)
			stateTime = 0;
		this.state = state;
		setShape(shapes.get(state));
		sword.setPosition(swordPositions.get(state));
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector2> getRenderables() {
		LinkedHashMap<TextureRegion, Vector2> map = new LinkedHashMap<TextureRegion, Vector2>();
		map.put(textures.get(getState()).getKeyFrame(stateTime), getPosition());
		map.putAll(sword.getRenderables());
		return map;
	}
}
