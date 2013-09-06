package com.dtlinley.abundantadversaries.gamestate;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class PauseState extends OverlayState {

	private final static TextureRegion pauseImage = new TextureRegion();
	private final static Vector2 pausePosition = new Vector2(100, 100);

	public PauseState(PlayState background, SpriteBatch batch) {
		super(background, batch, pauseImage, pausePosition);
	}

}
