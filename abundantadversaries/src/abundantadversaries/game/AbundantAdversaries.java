package abundantadversaries.game;

import abundantadversaries.input.InputHandler;
import abundantadversaries.screen.GameScreen;

import com.badlogic.gdx.Game;

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
