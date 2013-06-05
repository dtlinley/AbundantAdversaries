package com.dtlinley.toomanyninjas.entities;

import java.util.ArrayList;

import com.badlogic.gdx.math.Polygon;

public class Hero extends Entity {

	// TODO: private Sword sword;
	protected Hero(Polygon bounds) {
		super(bounds);
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
