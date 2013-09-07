package com.dtlinley.abundantadversaries.input;

import com.dtlinley.abundantadversaries.entities.Hero.HeroState;

public class UpdateableHeroStateController extends HeroStateController {

	public void update(float delta) {
		stateTime += delta;
		state = getNewState();
	}

	private HeroState getNewState() {
		HeroState desired = stateMapper.getStateFromInput();

		if (desired == state)
			return desired;

		if (state == HeroState.TRANSITION) {
			if (stateTime >= TRANSITION_TIME) {
				stateTime = 0;
				return desired;
			}
			return HeroState.TRANSITION;
		}

		stateTime = 0;
		return HeroState.TRANSITION;
	}
}
