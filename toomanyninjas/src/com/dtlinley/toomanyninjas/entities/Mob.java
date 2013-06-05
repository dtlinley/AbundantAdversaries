package com.dtlinley.toomanyninjas.entities;

import com.badlogic.gdx.math.Polygon;

/**
 * A mob (mobile entity) is a self-directed entity which can accelerate without outside assistance.
 * 
 * @author david
 * 
 */
public abstract class Mob extends Entity {

	protected Mob(Polygon bounds) {
		super(bounds);
	}

}
