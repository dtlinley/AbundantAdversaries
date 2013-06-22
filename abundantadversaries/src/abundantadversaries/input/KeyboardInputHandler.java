package abundantadversaries.input;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class KeyboardInputHandler implements InputHandler {

	private final HashMap<Integer, Vector2> inputs;

	public KeyboardInputHandler() {
		inputs = new HashMap<Integer, Vector2>();
		inputs.put(Input.Keys.RIGHT, new Vector2(1, 0));
		inputs.put(Input.Keys.UP, new Vector2(0, 1));
		inputs.put(Input.Keys.LEFT, new Vector2(-1, 0));
		inputs.put(Input.Keys.DOWN, new Vector2(0, -1));
	}

	@Override
	public boolean noInputGiven() {
		for (Integer i : inputs.keySet()) {
			if (Gdx.input.isKeyPressed(i))
				return false;
		}

		return true;
	}

	@Override
	public float getInputDirection() {
		Vector2 vec = new Vector2();
		for (Integer i : inputs.keySet()) {
			if (Gdx.input.isKeyPressed(i))
				vec.add(inputs.get(i).cpy());
		}

		return vec.angle();
	}

}
