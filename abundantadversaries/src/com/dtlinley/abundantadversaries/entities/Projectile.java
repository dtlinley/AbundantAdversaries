package com.dtlinley.abundantadversaries.entities;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

public class Projectile extends Mob {

	private boolean teamSwitch = false;

	protected TextureRegion image;

	public Projectile(Polygon bounds) {
		super(bounds);
		this.image = new TextureRegion(new Texture("bullet.png"));
	}

	@Override
	public LinkedHashMap<TextureRegion, Polygon> getRenderables() {
		LinkedHashMap<TextureRegion, Polygon> map = new LinkedHashMap<TextureRegion, Polygon>();
		Polygon p = getShape();
		map.put(image, p);
		return map;
	}

	public void deflect() {
		if (teamSwitch == false) {
			teamSwitch = true;
			setVelocity(getVelocity().cpy().mul(-1));
		}
	}

	public boolean shouldSwitchTeam() {
		return teamSwitch;
	}

	@Override
	public float getPoints() {
		return 1;
	}
}
