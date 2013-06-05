package com.dtlinley.toomanyninjas.entities;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Polygon;

public class Hero extends Entity {

	private Sword sword;
	private HashMap<HeroState, Animation> textures;

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

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

}
