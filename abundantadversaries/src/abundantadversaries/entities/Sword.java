package abundantadversaries.entities;

import java.util.HashMap;
import java.util.LinkedHashMap;

import abundantadversaries.entities.Hero.HeroState;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Sword extends Entity {

	private final HashMap<HeroState, Animation> textures;
	private final HashMap<HeroState, Polygon> shapes;
	private final HashMap<HeroState, Vector2> swordPositions;

	private HeroState state = HeroState.NEUTRAL;
	private float stateTime;

	protected Sword(Polygon shape) {
		super(shape);
		textures = new HashMap<HeroState, Animation>();
		shapes = new HashMap<HeroState, Polygon>();
		swordPositions = new HashMap<HeroState, Vector2>();
		Animation image = new Animation(100, new TextureRegion(new Texture("sword.png")));
		for (HeroState s : HeroState.values()) {
			// TODO: temporarily make all textures and shapes identical to avoid NPE
			textures.put(s, image);
			shapes.put(s, shape);
			swordPositions.put(s, new Vector2(100, 100));
		}
	}

	@Override
	public void collide() {
	}

	@Override
	public void update(float delta) {
		this.stateTime += delta;
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector2> getRenderables() {
		LinkedHashMap<TextureRegion, Vector2> map = new LinkedHashMap<TextureRegion, Vector2>();
		map.put(textures.get(getState()).getKeyFrame(stateTime), getPosition());
		return map;
	}

	public HeroState getState() {
		return state;
	}

	public void setState(HeroState state) {
		if (this.state == state)
			return;

		stateTime = 0;
		this.state = state;
		setShape(shapes.get(state));
		setPosition(swordPositions.get(state));
	}

}
