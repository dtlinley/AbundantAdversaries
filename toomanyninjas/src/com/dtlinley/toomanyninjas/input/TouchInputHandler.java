package com.dtlinley.toomanyninjas.input;

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

}
