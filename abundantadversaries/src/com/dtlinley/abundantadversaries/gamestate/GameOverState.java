package com.dtlinley.abundantadversaries.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class GameOverState extends OverlayState {

	private final int score;
	private final Vector2 scorePosition;
	private final BitmapFont font;

	public GameOverState(PlayState background, SpriteBatch batch, int score) {
		super(background, batch, new TextureRegion(), new Vector2(100, 100), new Vector2(100, 100));
		this.score = score;
		this.font = new BitmapFont();
		this.scorePosition = new Vector2(110, 110);
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.justTouched())
			target = new PlayState(batch);
	}

	@Override
	public void render() {
		super.render();
		batch.begin();
		font.draw(batch, String.valueOf(score), scorePosition.x, scorePosition.y);
		batch.end();
	}

}
