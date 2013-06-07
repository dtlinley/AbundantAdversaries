package com.dtlinley.toomanyninjas.screen;

import java.util.LinkedHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.dtlinley.toomanyninjas.gamestate.GameState;

/**
 * Screens are used in-game to manage GameStates. This involves changing the current GameState as well as telling the GameState to
 * update and draw. A GameScreen is a separate Screen in the game-- it could be the Main Menu, the Shop, the Achievements screen,
 * etc. A GameState is a state within that Screen, such as Paused, Playing, GameOver, etc.
 * 
 * @author david
 * 
 */

public class GameScreen implements Screen {

	private GameState state;
	private SpriteBatch batch;
	private OrthographicCamera camera;

	public GameScreen() {
		// this.state = new BeginState();
		this.batch = new SpriteBatch();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, h / w);
	}

	@Override
	public void render(float delta) {
		state = state.getTargetState();
		state.update(delta);

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		LinkedHashMap<TextureRegion, Vector2> textures = state.getRenderables();
		for (TextureRegion t : textures.keySet()) {
			Vector2 v = textures.get(t);
			batch.draw(t, v.x, v.y);
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
