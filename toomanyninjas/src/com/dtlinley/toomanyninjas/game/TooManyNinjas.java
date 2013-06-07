package com.dtlinley.toomanyninjas.game;

import com.badlogic.gdx.Game;
import com.dtlinley.toomanyninjas.screen.GameScreen;

public class TooManyNinjas extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		getScreen().dispose();
	}

	@Override
	public void render() {
		super.render();
	}
}
