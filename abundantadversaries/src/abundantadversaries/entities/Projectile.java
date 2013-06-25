package abundantadversaries.entities;

import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Mob {

	private boolean teamSwitch = false;

	protected TextureRegion image;

	public Projectile(Polygon bounds) {
		super(bounds);
		this.image = new TextureRegion(new Texture("bullet.png"));
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector2> getRenderables() {
		LinkedHashMap<TextureRegion, Vector2> map = new LinkedHashMap<TextureRegion, Vector2>();
		map.put(image, getPosition());
		return map;
	}

	public void deflect() {
		teamSwitch = true;
		setVelocity(getVelocity().cpy().mul(-1));
	}

	public boolean shouldSwitchTeam() {
		return teamSwitch;
	}

}
