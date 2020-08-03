package user;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.components.animation.StaticTexture;
import engine.components.collisionbox.CollisionBox;
import engine.components.entities.Entity;

public class Bullet extends Entity{

	StaticTexture static_texture;
	CollisionBox collision_box;
	
	public Bullet(Vector3f position, Vector2f pixel_scale, float rotation) {
		super(position, pixel_scale, rotation);
		static_texture = new StaticTexture("res/sprites/bullet.png");
		collision_box = new CollisionBox(this, true, "Bullet");
	}

	@Override
	public int GetTextureId() {
		return static_texture.getTextureId();
	}

	private float bullet_speed = 10;
	
	@Override
	public void Update() {
		SetDeltaPosition(new Vector2f(0, -bullet_speed));
		
		if(GetPosition().y < 0) {
			DestroyEntity(this);
			DestroyEntity(collision_box);
		}
	}
	
}
