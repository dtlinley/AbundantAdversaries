package com.dtlinley.abundantadversaries.input;

import com.dtlinley.abundantadversaries.entities.Hero.HeroState;
import com.dtlinley.abundantadversaries.game.AbundantAdversaries;

/**
 * The HeroStateController takes the user's desired HeroState from an InputStateMapper and decides whether or not the user is
 * worthy of their desired state. Base class is not updateable-- if multiple objects have a reference to a HSC, only one should be
 * able to update it
 * 
 * @author david
 * 
 */
public class HeroStateController {

	protected static final float TRANSITION_TIME = 0.1f;
	protected final InputStateMapper stateMapper;

	protected HeroState state;
	protected float stateTime;

	public HeroStateController() {
		stateMapper = new InputStateMapper(AbundantAdversaries.getInputHandler());
		state = HeroState.NEUTRAL;
	}

	public HeroState getState() {
		return state;
	}

	public float getStateTime() {
		return stateTime;
	}
}
