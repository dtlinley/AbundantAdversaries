package com.dtlinley.abundantadversaries.gamestate;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class StartState extends OverlayState {

	private final static TextureRegion startImage = new TextureRegion();
	private final static Vector2 startPosition = new Vector2(100, 100);

	public StartState(PlayState background, SpriteBatch batch) {
		super(background, batch, startImage, startPosition);
	}

}
