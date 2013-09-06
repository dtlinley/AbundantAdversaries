package com.dtlinley.abundantadversaries.client;

import com.dtlinley.abundantadversaries.game.AbundantAdversaries;
import com.dtlinley.abundantadversaries.input.KeyboardInputHandler;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig() {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(480, 320);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener() {
		return new AbundantAdversaries(new KeyboardInputHandler());
	}
}