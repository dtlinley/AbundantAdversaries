package com.dtlinley.toomanyninjas.game;

import com.dtlinley.toomanyninjas.entities.Hero;

public class World {

	private final Hero hero;
	private final EnemyDirector director;

	public World() {
		// TODO: add hero polygon
		hero = new Hero(null);
		director = new EnemyDirector();
	}

	public void update(float delta) {
		hero.update(delta);
		director.update(delta);
	}
}
