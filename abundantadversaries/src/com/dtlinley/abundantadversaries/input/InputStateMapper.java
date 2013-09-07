package com.dtlinley.abundantadversaries.input;

import java.util.HashMap;

import com.dtlinley.abundantadversaries.entities.Hero.HeroState;

/**
 * Maps between an InputHandler and the HeroState which corresponds to the input reported by the InputHandler. While the IH reads
 * the input based on the appropriate signals (touch input, keyboard input, etc), the ISM will take the overall direction of the
 * input and map it to a corresponding HeroState. This does not handle any special cases or rules about Heros switching states.
 * Its purpose is to take the user's input and convert that into a HeroState that represents what the user wants the Hero to do
 * 
 * @author david
 * 
 */
public class InputStateMapper {

	private final InputHandler handler;
	private final HashMap<Float, HeroState> directionStates;

	public InputStateMapper(InputHandler handler) {
		this.handler = handler;
		this.directionStates = new HashMap<Float, HeroState>();
		directionStates.put(0f, HeroState.RIGHT);
		directionStates.put(45f, HeroState.UP_RIGHT);
		directionStates.put(90f, HeroState.UP);
		directionStates.put(135f, HeroState.UP_LEFT);
		directionStates.put(180f, HeroState.LEFT);
		directionStates.put(225f, HeroState.DOWN_LEFT);
		directionStates.put(270f, HeroState.UP);
		directionStates.put(315f, HeroState.DOWN_RIGHT);
	}

	public HeroState getStateFromInput() {
		if (handler.noInputGiven())
			return HeroState.NEUTRAL;

		float direction = handler.getInputDirection();
		float min = 360f;
		float match = 0f;
		for (Float f : directionStates.keySet()) {
			float diff = Math.abs(f - direction);
			if (diff < min) {
				match = f;
				min = diff;
			}
		}

		return directionStates.get(match);
	}
}
