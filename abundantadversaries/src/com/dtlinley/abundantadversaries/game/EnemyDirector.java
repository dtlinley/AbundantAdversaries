package com.dtlinley.abundantadversaries.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.dtlinley.abundantadversaries.entities.Enemy;
import com.dtlinley.abundantadversaries.entities.Entity;
import com.dtlinley.abundantadversaries.entities.Mob;
import com.dtlinley.abundantadversaries.entities.Projectile;

/**
 * Tracks currently alive enemy and allied entities-- does not check for collision between enemies and allies (which is handled
 * with collision checking between the hero and enemy entities by the World). Keeps track of the number of enemies killed, spawns
 * new enemies when appropriate and manages how fast enemies spawn.
 * 
 * @author david
 * 
 */
public class EnemyDirector implements Renderable {
	private final ArrayList<Projectile> enemyProjectiles = new ArrayList<Projectile>();
	private final ArrayList<Projectile> allies = new ArrayList<Projectile>();
	private final ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private final int KILLS_PER_LEVEL = 10;

	private int enemiesKilled;
	private int level;
	private float spawnSpeed; // how many ms (on average) elapse between spawned mobs
	private float toNextSpawn; // ms until another mob should be spawned
	private Random r;

	public EnemyDirector() {
		enemiesKilled = 0;
		level = 1;
		spawnSpeed = 5f;
		r = new Random();
		toNextSpawn = (float) ((r.nextGaussian() * (0.2 * spawnSpeed)) + spawnSpeed);
	}

	public void update(float delta) {
		// update spawn timer, spawn new enemies as necessary
		toNextSpawn -= delta;
		if (toNextSpawn <= 0) {
			// TODO: spawn a mob
			for (int i = 0; i < 1; i++) {
				Projectile bullet = new Projectile(new Polygon(new float[] { 0f, 0f, 10f, 0f, 10f, 5f, 0f, 5f }));
				bullet.setVelocity(new Vector2(128f, 0f));
				bullet.setPosition(new Vector2(-200, -80));
				enemyProjectiles.add(bullet);
			}
			toNextSpawn = (float) ((r.nextGaussian() * (0.2 * spawnSpeed)) + spawnSpeed);
		}

		// update all enemies and allies
		for (Projectile p : enemyProjectiles) {
			p.update(delta);
		}
		for (Projectile p : allies) {
			p.update(delta);
		}
		for (Enemy e : enemies) {
			e.update(delta);
		}

		// clean the list of enemies, remove all that should be removed
		ArrayList<Mob> toRemove = new ArrayList<Mob>();
		ArrayList<Projectile> newAllies = new ArrayList<Projectile>();
		for (Projectile e : enemyProjectiles) {
			if (e.isDead()) {
				toRemove.add(e);
			} else if (e.shouldSwitchTeam()) {
				newAllies.add(e);
			}
		}
		for (Enemy e : enemies) {
			if (e.isDead())
				toRemove.add(e);
		}

		enemyProjectiles.removeAll(toRemove);
		enemies.removeAll(toRemove);
		enemiesKilled += toRemove.size();

		enemyProjectiles.removeAll(newAllies);
		allies.addAll(newAllies);

		level = (int) Math.floor(enemiesKilled / (double) KILLS_PER_LEVEL);
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<Projectile> getEnemyProjectiles() {
		return enemyProjectiles;
	}

	public int getLevel() {
		return level;
	}

	public int getScore() {
		return enemiesKilled;
	}

	@Override
	public LinkedHashMap<TextureRegion, Polygon> getRenderables() {
		LinkedHashMap<TextureRegion, Polygon> textures = new LinkedHashMap<TextureRegion, Polygon>();
		for (Entity e : enemies) {
			textures.putAll(e.getRenderables());
		}
		for (Entity e : enemyProjectiles) {
			textures.putAll(e.getRenderables());
		}
		for (Entity e : allies) {
			textures.putAll(e.getRenderables());
		}
		return textures;
	}
}
