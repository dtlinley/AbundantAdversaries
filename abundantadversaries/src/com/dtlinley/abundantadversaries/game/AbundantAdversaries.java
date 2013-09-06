package com.dtlinley.abundantadversaries.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.dtlinley.abundantadversaries.input.InputHandler;
import com.dtlinley.abundantadversaries.screen.GameScreen;

public class AbundantAdversaries extends Game {

	private static InputHandler input;

	// FIXME: still not thrilled with how this is handled, but at least random things can't change the IH now
	public static InputHandler getInputHandler() {
		return input;
	}

	public AbundantAdversaries(InputHandler ih) {
		input = ih;
	}

	@Override
	public void create() {
		Gdx.input.setInputProcessor(AbundantAdversaries.input.getInputProcessor());
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
