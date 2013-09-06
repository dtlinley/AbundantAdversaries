package com.dtlinley.abundantadversaries.entities;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;

public class Projectile extends Mob {

	private boolean teamSwitch = false;

	protected TextureRegion image;

	public Projectile(Polygon bounds) {
		super(bounds);
		this.image = new TextureRegion(new Texture("bullet.png"));
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector3> getRenderables() {
		LinkedHashMap<TextureRegion, Vector3> map = new LinkedHashMap<TextureRegion, Vector3>();
		Vector3 v = new Vector3(getPosition().x, getPosition().y, getRotation());
		map.put(image, v);
		return map;
	}

	public void deflect() {
		teamSwitch = true;
		setVelocity(getVelocity().cpy().mul(-1));
	}

	public boolean shouldSwitchTeam() {
		return teamSwitch;
	}

}
