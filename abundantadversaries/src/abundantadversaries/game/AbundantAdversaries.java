package abundantadversaries.game;

import abundantadversaries.input.InputHandler;
import abundantadversaries.screen.GameScreen;

import com.badlogic.gdx.Game;

public class AbundantAdversaries extends Game {

	// XXX: Static field which needs to be set by platform-specific apps or else the game will crash. Not thrilling.
	public static InputHandler input;

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
