package abundantadversaries.entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Mob {

	ArrayList<Projectile> newProjectiles = new ArrayList<Projectile>();

	protected Enemy(Polygon bounds) {
		super(bounds);
	}

	@Override
	public void collide() {
		dead = true;
	}

	@Override
	public LinkedHashMap<TextureRegion, Vector2> getRenderables() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Projectile> getNewProjectiles() {
		return newProjectiles;
	}

	@Override
	public void update(float delta) {
		super.update(delta);
	}

}
