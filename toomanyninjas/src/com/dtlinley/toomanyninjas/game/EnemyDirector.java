package com.dtlinley.toomanyninjas.game;

import java.util.ArrayList;
import java.util.Random;

import com.dtlinley.toomanyninjas.entities.Entity;

/**
 * Tracks currently alive enemy and allied entities-- does not check for collision between enemies and allies (which is handled
 * with collision checking between the hero and enemy entities by the World). Keeps track of the number of enemies killed, spawns
 * new enemies when appropriate and manages how fast enemies spawn.
 * 
 * @author david
 * 
 */
public class EnemyDirector {
	private final ArrayList<Entity> enemies = new ArrayList<Entity>();
	private final ArrayList<Entity> allies = new ArrayList<Entity>();
	private final int KILLS_PER_LEVEL = 10;

	private int enemiesKilled;
	private int level;
	private int spawnSpeed; // how many ms (on average) elapse between spawned mobs
	private int toNextSpawn; // ms until another mob should be spawned
	private Random r;

	public EnemyDirector() {
		enemiesKilled = 0;
		level = 1;
		spawnSpeed = 3000;
		toNextSpawn = (int) ((r.nextGaussian() * (0.2 * spawnSpeed)) + spawnSpeed);
	}

	public void update(float delta) {
		// update spawn timer, spawn new enemies as necessary
		toNextSpawn -= delta;
		if (toNextSpawn <= 0) {
			// TODO: spawn a mob
			toNextSpawn = (int) ((r.nextGaussian() * (0.2 * spawnSpeed)) + spawnSpeed);
		}

		// clean the list of enemies, remove all that should be removed
		ArrayList<Entity> toRemove = new ArrayList<Entity>();
		ArrayList<Entity> newAllies = new ArrayList<Entity>();
		for (Entity e : enemies) {
			if (e.isDead()) {
				toRemove.add(e);
			} else if (e.shouldSwitchTeam()) {
				newAllies.add(e);
			}
		}

		enemies.removeAll(toRemove);
		enemiesKilled += toRemove.size();

		enemies.removeAll(newAllies);
		allies.addAll(newAllies);

		level = (int) Math.floor(enemiesKilled / (double) KILLS_PER_LEVEL);
	}

	public ArrayList<Entity> getEnemies() {
		return enemies;
	}

	public int getLevel() {
		return level;
	}
}
