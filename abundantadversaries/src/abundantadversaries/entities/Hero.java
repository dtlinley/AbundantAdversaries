package abundantadversaries.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Hero extends Entity {

	private Sword sword;
	private final HashMap<HeroState, Animation> textures;
	private final HashMap<HeroState, Polygon> shapes;
	private final HashMap<HeroState, Vector2> swordPositions;
	private float stateTime;
	private HeroState state;

	public enum HeroState {
		LEFT, DOWN_LEFT, UP_LEFT, UP, RIGHT, DOWN_RIGHT, UP_RIGHT, NEUTRAL, TRANSITION
	};

	public Hero(Polygon bounds) {
		super(bounds);
		// TODO: real sword polygon
		sword = new Sword(null);
		textures = new HashMap<HeroState, Animation>();
		shapes = new HashMap<HeroState, Polygon>();
		swordPositions = new HashMap<HeroState, Vector2>();
		Animation image = new Animation(100, new TextureRegion(new Texture("hero.png")));
		for (HeroState s : HeroState.values()) {
			// TODO: temporarily make all textures, shapes and swordPos' identical to avoid NPE
			textures.put(s, image);
			shapes.put(s, bounds);
			swordPositions.put(s, getPosition());
		}
	}

	public void deflect(ArrayList<Enemy> enemies, ArrayList<Projectile> projectiles) {
		for (Projectile p : projectiles) {
			if (sword.collidesWith(p))
				p.deflect();
		}
		for (Enemy e : enemies) {
			if (sword.collidesWith(e))
				e.collide();
		}
	}

	public void checkForDeath(ArrayList<Entity> entities) {
		for (Entity e : entities) {
			if (e.collidesWith(this)) {
				collide();
				return;
			}
		}
	}

	@Override
	public void update(float delta) {
		stateTime += delta;
		HeroState state = getStateFromInput();
		setState(state);
	}

	private HeroState getState() {
		return state;
	}

	private void setState(HeroState state) {
		if (this.state == state)
			return;

		stateTime = 0;
		this.state = state;
		setShape(shapes.get(state));
		sword.setPosition(swordPositions.get(state));
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector2> getRenderables() {
		LinkedHashMap<TextureRegion, Vector2> map = new LinkedHashMap<TextureRegion, Vector2>();
		map.put(textures.get(getState()).getKeyFrame(stateTime), getPosition());
		map.putAll(sword.getRenderables());
		return map;
	}

	/**
	 * Read the input and determine the appropriate state for the Hero to be in. If the user is holding the up and left arrow
	 * keys, for instance (and the transition animation is finished) then the target state would be UP_LEFT
	 */
	private HeroState getStateFromInput() {
		// if the current state is TRANSITION and the stateTime is less than transitionTime, return TRANSITION
		if ((getState() == HeroState.TRANSITION) && (stateTime < textures.get(HeroState.TRANSITION).animationDuration))
			return HeroState.TRANSITION;

		// TODO
		// else, map the direction of input to the appropriate other state
		return HeroState.NEUTRAL;
	}

	@Override
	public Polygon getShape() {
		return shapes.get(state);
	}
}
