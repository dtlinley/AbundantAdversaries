package abundantadversaries.input;

import com.badlogic.gdx.Gdx;

public class TouchInputHandler implements InputHandler {

	@Override
	public boolean noInputGiven() {
		return !Gdx.input.isTouched();
	}

	@Override
	public float getInputDirection() {
		return 0;
	}

	@Override
	public boolean shouldStartGame() {
		return Gdx.input.justTouched();
	}

}
