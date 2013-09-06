package com.dtlinley.abundantadversaries;

import com.dtlinley.abundantadversaries.game.AbundantAdversaries;
import com.dtlinley.abundantadversaries.input.TouchInputHandler;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = false;

		initialize(new AbundantAdversaries(new TouchInputHandler()), cfg);
	}
}