package com.dtlinley.abundantadversaries.game;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;

public interface Renderable {

	/**
	 * Game States will never render their own materials. Instead, it is collected by the Screen which coordinates all drawing in
	 * order to gain efficiency.
	 * 
	 * @return
	 */
	public LinkedHashMap<TextureRegion, Polygon> getRenderables();

}
