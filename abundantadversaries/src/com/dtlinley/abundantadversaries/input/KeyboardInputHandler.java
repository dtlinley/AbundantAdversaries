package com.dtlinley.abundantadversaries.input;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;

public class KeyboardInputHandler implements InputHandler {

	private final HashMap<Integer, Vector2> inputs;
	private final StartGameInputProcessor processor;

	private class StartGameInputProcessor extends InputMultiplexer {
		private boolean spacePressed = false;

		@Override
		public boolean keyUp(int keycode) {
			if (keycode == Input.Keys.SPACE) {
				spacePressed = true;
				return true;
			}

			return false;
		}

		public boolean spacePressed() {
			if (spacePressed) {
				spacePressed = false;
				return true;
			}

			return false;
		}
	}

	public KeyboardInputHandler() {
		inputs = new HashMap<Integer, Vector2>();
		inputs.put(Input.Keys.RIGHT, new Vector2(1, 0));
		inputs.put(Input.Keys.UP, new Vector2(0, 1));
		inputs.put(Input.Keys.LEFT, new Vector2(-1, 0));
		inputs.put(Input.Keys.DOWN, new Vector2(0, -1));
		processor = new StartGameInputProcessor();
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

	@Override
	public boolean shouldStartGame() {
		return processor.spacePressed();
	}

	public InputMultiplexer getInputProcessor() {
		return processor;
	}

}
