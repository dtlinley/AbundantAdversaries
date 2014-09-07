package com.dtlinley.abundantadversaries.entities;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.dtlinley.abundantadversaries.game.behaviours.LinearMovement;

public class Projectile extends Mob {

	private boolean teamSwitch = false;
	private final Vector2 initialDirection;

	protected TextureRegion image;

	public Projectile(Polygon bounds) {
		super(bounds, new LinearMovement(100));
		image = new TextureRegion(new Texture("bullet.png"));
		initialDirection = Hero.getHeroPosition().sub(getPosition());
	}

	public void deflect() {
		if (teamSwitch == false) {
			teamSwitch = true;
			currentManeuver = behaviour.getNextManeuver(getPosition(), initialDirection.mul(-1));
		}
	}

	@Override
	public float getPoints() {
		return 1;
	}

	@Override
	public LinkedHashMap<TextureRegion, Polygon> getRenderables() {
		LinkedHashMap<TextureRegion, Polygon> map = new LinkedHashMap<TextureRegion, Polygon>();
		Polygon p = getShape();
		map.put(image, p);
		return map;
	}

	public boolean shouldSwitchTeam() {
		return teamSwitch;
	}
}
