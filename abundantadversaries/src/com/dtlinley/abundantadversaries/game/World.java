package com.dtlinley.abundantadversaries.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.dtlinley.abundantadversaries.entities.Enemy;
import com.dtlinley.abundantadversaries.entities.Entity;
import com.dtlinley.abundantadversaries.entities.Hero;
import com.dtlinley.abundantadversaries.entities.Projectile;

public class World implements Renderable {

	private final float GROUND_LEVEL = -50;

	private final Hero hero;
	private final EnemyDirector director;
	private final ArrayList<Entity> entities = new ArrayList<Entity>();

	private float degree = 0;

	public World() {
		float[] points = { -32f, -32f, -32f, 32f, 32f, 32f, 32f, -32f };
		Polygon heroPoly = new Polygon(points);
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
	public LinkedHashMap<TextureRegion, Vector3> getRenderables() {
		LinkedHashMap<TextureRegion, Vector3> textures = hero.getRenderables();
		textures.putAll(director.getRenderables());
		return textures;
	}

	public int getScore() {
		return director.getScore();
	}

	public boolean isGameOver() {
		return hero.isDead();
	}
}
