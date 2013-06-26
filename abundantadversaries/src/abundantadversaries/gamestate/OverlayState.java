package abundantadversaries.gamestate;

import java.util.LinkedHashMap;

import abundantadversaries.game.AbundantAdversaries;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Any GameState that simply presents the PlayState frozen with an overlay displaying some information. There is some input which
 * signals universally that the game should resume.
 * 
 * @author david
 * 
 */
public abstract class OverlayState extends PlayState {

	protected final PlayState background;
	protected final TextureRegion overlay;
	protected final Vector2 overlayPosition;

	public OverlayState(PlayState background, SpriteBatch batch, TextureRegion overlay, Vector2 overlayPosition) {
		super(batch);
		this.background = background;
		this.overlay = overlay;
		this.overlayPosition = overlayPosition;

		target = this;
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector2> getRenderables() {
		LinkedHashMap<TextureRegion, Vector2> map = super.getRenderables();
		map.put(overlay, overlayPosition);
		return map;
	}

	@Override
	public void update(float delta) {
		if (AbundantAdversaries.getInputHandler().shouldStartGame())
			target = background;
	}
}
