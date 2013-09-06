package com.dtlinley.abundantadversaries.gamestate;

import java.util.LinkedHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.dtlinley.abundantadversaries.game.World;

public class PlayState implements GameState {

	protected GameState target;
	protected World world;
	protected SpriteBatch batch;

	public PlayState(SpriteBatch batch) {
		world = new World();
		target = this;
		this.batch = batch;
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
	public LinkedHashMap<TextureRegion, Vector3> getRenderables() {
		return world.getRenderables();
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.

		batch.begin();
		LinkedHashMap<TextureRegion, Vector3> textures = getRenderables();
		for (TextureRegion t : textures.keySet()) {
			Vector3 v = textures.get(t);
			draw(batch, t, v.x, v.y, v.z);
		}
		batch.end();
	}

	private void draw(SpriteBatch batch, TextureRegion t, float x, float y, float rotation) {
		float w = t.getTexture().getWidth();
		float h = t.getTexture().getHeight();
		float origX = (w / 2f);
		float origY = (h / 2f);
		batch.draw(t, x, y, origX, origY, w, h, 1f, 1f, rotation);
	}
}
