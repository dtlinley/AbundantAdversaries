package com.dtlinley.abundantadversaries.gamestate;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class PauseState extends OverlayState {

	public PauseState(PlayState background, SpriteBatch batch) {
		super(background, batch, new TextureRegion(), new Vector2(100, 100), new Vector2(100, 100));
	}

}
