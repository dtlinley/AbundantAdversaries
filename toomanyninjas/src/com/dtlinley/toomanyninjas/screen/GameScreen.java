package com.dtlinley.toomanyninjas.screen;

import com.badlogic.gdx.Screen;
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

	public GameScreen() {
		// this.state = new BeginState();
	}

	@Override
	public void render(float delta) {
		state = state.getTargetState();
		state.update(delta);
		state.render(delta);
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
		// TODO Auto-generated method stub

	}

}
