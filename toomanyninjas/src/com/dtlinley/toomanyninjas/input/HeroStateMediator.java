package com.dtlinley.toomanyninjas.input;

import java.util.HashMap;

import com.dtlinley.toomanyninjas.entities.Hero.HeroState;



/**
 * Mediates between an InputHandler and the HeroState which corresponds to the input reported by the InputHandler. While the IH
 * reads the input based on the appropriate signals (touch input, keyboard input, etc), the HSM will take the overall direction of
 * the input and map it to a corresponding HeroState. The Hero will then have a HSM (initialized with an IH) which it will use to
 * automatically get the appropriate state during updating.
 * 
 * @author david
 * 
 */
public class HeroStateMediator {

	private final InputHandler handler;
	private final HashMap<Float, HeroState> directionStates;

	public HeroStateMediator(InputHandler handler) {
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
