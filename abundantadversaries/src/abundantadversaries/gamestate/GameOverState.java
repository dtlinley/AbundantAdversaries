package abundantadversaries.gamestate;

import java.util.LinkedHashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class GameOverState extends PlayState {

	private final int score;
	private final TextureRegion overlay;
	private final Vector2 overlayPosition;
	private final Vector2 scorePosition;
	private final BitmapFont font;

	private GameState target;

	public GameOverState(SpriteBatch batch, int score) {
		super(batch);
		this.score = score;
		target = this;
		// TODO: actually load an image
		this.overlay = new TextureRegion();
		this.overlayPosition = new Vector2(100, 100);
		this.font = new BitmapFont();
		this.scorePosition = new Vector2(110, 110);
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.justTouched())
			target = new PlayState(batch);
	}

	@Override
	public GameState getTargetState() {
		return target;
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector2> getRenderables() {
		LinkedHashMap<TextureRegion, Vector2> map = super.getRenderables();
		map.put(overlay, overlayPosition);
		return map;
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.

		LinkedHashMap<TextureRegion, Vector2> textures = getRenderables();
		batch.begin();
		for (TextureRegion t : textures.keySet()) {
			Vector2 v = textures.get(t);
			batch.draw(t, v.x, v.y);
		}
		font.draw(batch, String.valueOf(score), scorePosition.x, scorePosition.y);
		batch.end();
	}

}
