package com.dtlinley.abundantadversaries.entities.enemies;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

public class KillBot extends Enemy {

	private final static Polygon bounds = new Polygon(new float[] { 0f, 0f, 0f, 64f, 64f, 64f, 64f, 0f });

	public KillBot() {
		super(bounds, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinkedHashMap<TextureRegion, Polygon> getRenderables() {
		// TODO Auto-generated method stub
		return null;
	}

}
