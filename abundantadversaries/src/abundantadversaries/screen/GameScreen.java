package abundantadversaries.screen;

import abundantadversaries.gamestate.GameState;
import abundantadversaries.gamestate.PlayState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
		state = new PlayState(batch);
	}

	@Override
	public void render(float delta) {
		state = state.getTargetState();
		state.update(delta);
		batch.setProjectionMatrix(camera.combined);
		state.render();
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
