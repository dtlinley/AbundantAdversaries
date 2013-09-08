package com.dtlinley.abundantadversaries.gamestate;

import java.util.LinkedHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
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
	public LinkedHashMap<TextureRegion, Polygon> getRenderables() {
		return world.getRenderables();
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.

		batch.begin();
		LinkedHashMap<TextureRegion, Polygon> textures = getRenderables();
		for (TextureRegion t : textures.keySet()) {
			Polygon p = textures.get(t);
			draw(batch, t, p);
		}
		batch.end();
	}

	private void draw(SpriteBatch batch, TextureRegion t, Polygon p) {
		// Note: does not properly draw polygons with the bottom-left corner at a point other than (0,0)
		Polygon localPoly = new Polygon(p.getVertices());
		Rectangle pRect = localPoly.getBoundingRectangle();
		float x = p.getX();
		float y = p.getY();
		float w = pRect.width;
		float h = pRect.height;
		float r = p.getRotation();
		float origX = p.getOriginX();
		float origY = p.getOriginY();
		batch.draw(t, x, y, origX, origY, w, h, 1f, 1f, r);
	}
}
