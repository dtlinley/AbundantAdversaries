package com.dtlinley.toomanyninjas.entities;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

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
	public LinkedHashMap<TextureRegion, Vector2> getRenderables() {
		// TODO: fetch appropriate texture once they are loaded
		return null;
	}

}
