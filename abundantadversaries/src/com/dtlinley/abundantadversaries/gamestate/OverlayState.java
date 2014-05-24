package com.dtlinley.abundantadversaries.gamestate;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.dtlinley.abundantadversaries.game.AbundantAdversaries;

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
	protected final Vector2 overlaySize;

	public OverlayState(PlayState background, SpriteBatch batch, TextureRegion overlay, Vector2 overlayPosition,
			Vector2 overlaySize) {
		super(batch);
		this.background = background;
		this.overlay = overlay;
		this.overlayPosition = overlayPosition;
		this.overlaySize = overlaySize;

		target = this;
	}

	@Override
	public LinkedHashMap<TextureRegion, Polygon> getRenderables() {
		LinkedHashMap<TextureRegion, Polygon> map = super.getRenderables();
		float orgX = overlayPosition.x;
		float orgY = overlayPosition.y;
		float sizeX = overlaySize.x;
		float sizeY = overlaySize.y;
		Polygon v = new Polygon(new float[] { orgX, orgY, orgX + sizeX, orgY, orgX + sizeX, orgY + sizeY, orgX, orgY + sizeY });
		map.put(overlay, v);
		return map;
	}

	@Override
	public void update(float delta) {
		if (AbundantAdversaries.getInputHandler().shouldStartGame())
			target = background;
	}
}
