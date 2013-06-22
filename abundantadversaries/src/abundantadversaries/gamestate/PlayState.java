package abundantadversaries.gamestate;

import java.util.LinkedHashMap;

import abundantadversaries.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class PlayState implements GameState {

	protected GameState target;
	protected World world;
	protected SpriteBatch batch;

	public PlayState(SpriteBatch batch) {
		world = new World();
		target = this;
	}

	@Override
	public void update(float delta) {
		world.update(delta);

		if (world.isGameOver()) {
			target = new GameOverState(this, batch, world.getScore());
		}
	}

	@Override
	public GameState getTargetState() {
		return target;
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector2> getRenderables() {
		return world.getRenderables();
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.

		batch.begin();
		LinkedHashMap<TextureRegion, Vector2> textures = getRenderables();
		for (TextureRegion t : textures.keySet()) {
			Vector2 v = textures.get(t);
			batch.draw(t, v.x, v.y);
		}
		batch.end();
	}

}
