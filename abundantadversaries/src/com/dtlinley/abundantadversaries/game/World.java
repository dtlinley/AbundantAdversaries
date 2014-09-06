package com.dtlinley.abundantadversaries.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.dtlinley.abundantadversaries.entities.Entity;
import com.dtlinley.abundantadversaries.entities.Hero;
import com.dtlinley.abundantadversaries.entities.Projectile;
import com.dtlinley.abundantadversaries.entities.enemies.Enemy;

public class World implements Renderable {

	private final float GROUND_LEVEL = -100;

	private final Hero hero;
	private final EnemyDirector director;
	private final ArrayList<Entity> entities = new ArrayList<Entity>();

	public World() {
		float[] points = { 0f, 0f, 0f, 64f, 64f, 64f, 64f, 0f };
		Polygon heroPoly = new Polygon(points);
		heroPoly.setOrigin(32, 32);
		hero = new Hero(heroPoly);
		hero.setPosition(new Vector2(-32, GROUND_LEVEL));
		director = new EnemyDirector();
	}

	public void update(float delta) {
		ArrayList<Enemy> enemies = director.getEnemies();
		ArrayList<Projectile> projectiles = director.getEnemyProjectiles();
		entities.clear();
		entities.addAll(projectiles);
		entities.addAll(enemies);
		hero.deflect(enemies, projectiles);
		hero.checkForDeath(entities);
		hero.update(delta);
		director.update(delta);
	}

	@Override
	public LinkedHashMap<TextureRegion, Polygon> getRenderables() {
		LinkedHashMap<TextureRegion, Polygon> textures = hero.getRenderables();
		textures.putAll(director.getRenderables());
		return textures;
	}

	public float getScore() {
		return director.getScore();
	}

	public boolean isGameOver() {
		return hero.isDead();
	}
}
