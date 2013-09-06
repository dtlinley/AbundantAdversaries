package com.dtlinley.abundantadversaries;

import com.dtlinley.abundantadversaries.game.AbundantAdversaries;
import com.dtlinley.abundantadversaries.input.KeyboardInputHandler;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "abundantadversaries";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 320;

		new LwjglApplication(new AbundantAdversaries(new KeyboardInputHandler()), cfg);
	}
}
