package abundantadversaries.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import abundantadversaries.entities.Enemy;
import abundantadversaries.entities.Entity;
import abundantadversaries.entities.Hero;
import abundantadversaries.entities.Projectile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class World {

	private final Hero hero;
	private final EnemyDirector director;
	private final ArrayList<Entity> entities = new ArrayList<Entity>();

	public World() {
		// TODO: add hero polygon
		hero = new Hero(null);
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

	public LinkedHashMap<TextureRegion, Vector2> getRenderables() {
		LinkedHashMap<TextureRegion, Vector2> textures = hero.getRenderables();
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
