package com.dtlinley.toomanyninjas.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Polygon;

public class Sword extends Entity {

	// TODO: private HashMap<HeroState, Animation> textures;

	protected Sword(Polygon shape) {
		// TODO: automatic sword polygon
		super(shape);
		// TODO: textures = blah blah
	}

	@Override
	public void collide() {
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void render() {
	}

	public ArrayList<Animation> getRenderables() {
		// TODO: fetch appropriate texture once they are loaded
		return null;
	}

}
