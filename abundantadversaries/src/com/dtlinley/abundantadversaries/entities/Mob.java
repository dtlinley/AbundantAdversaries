package com.dtlinley.abundantadversaries.entities;

import com.badlogic.gdx.math.Polygon;
import com.dtlinley.abundantadversaries.game.behaviours.Behaviour;
import com.dtlinley.abundantadversaries.game.maneuvers.Maneuver;

/**
 * A mob (mobile entity) is a self-directed entity which can accelerate without outside assistance.
 * 
 * @author david
 * 
 */
public abstract class Mob extends Entity {

	protected Behaviour behaviour;
	protected Maneuver currentManeuver;

	protected Mob(Polygon bounds, Behaviour behaviour) {
		super(bounds);
		this.behaviour = behaviour;
		currentManeuver = behaviour.getNextManeuver(getPosition(), Hero.getHeroPosition());
	}

	/*
	 * The total number of points a particular Mob gives out for being destroyed
	 */
	public abstract float getPoints();

	@Override
	public void update(float delta) {
		if (currentManeuver.isFinished())
			currentManeuver = behaviour.getNextManeuver(getPosition(), Hero.getHeroPosition());
		this.setPosition(currentManeuver.getNextPosition(delta));
	}
}
