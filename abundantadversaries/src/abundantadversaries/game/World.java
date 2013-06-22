package abundantadversaries.game;

import java.util.LinkedHashMap;

import abundantadversaries.entities.Hero;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

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
