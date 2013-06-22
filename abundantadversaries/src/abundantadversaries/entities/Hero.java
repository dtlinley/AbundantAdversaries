package abundantadversaries.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Hero extends Entity {

	private Sword sword;
	private HashMap<HeroState, Animation> textures;
	private HashMap<HeroState, Polygon> shapes;
	private HashMap<HeroState, Vector2> swordPositions;
	private float stateTime;
	private HeroState state;

	public enum HeroState {
		LEFT, DOWN_LEFT, UP_LEFT, UP, RIGHT, DOWN_RIGHT, UP_RIGHT, NEUTRAL, TRANSITION
	};

	public Hero(Polygon bounds) {
		super(bounds);
		sword = new Sword(null);
	}

	public void deflect(ArrayList<Entity> entities) {
		// TODO: scan through list of entities, find those that collide with the sword, do damage to them
	}

	public void checkForDeath(ArrayList<Entity> entities) {
		// TODO: scan list of entities, find those colliding with self-- if one found, die
	}

	@Override
	public void collide() {
		dead = true;
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
}