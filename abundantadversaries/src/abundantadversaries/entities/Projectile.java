package abundantadversaries.entities;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public abstract class Projectile extends Mob {

	private boolean teamSwitch = false;

	protected Projectile(Polygon bounds) {
		super(bounds);
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector2> getRenderables() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deflect() {
		teamSwitch = true;
		setVelocity(getVelocity().cpy().mul(-1));
	}

	public boolean shouldSwitchTeam() {
		return teamSwitch;
	}

}
