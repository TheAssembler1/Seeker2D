package user;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.components.animation.StaticTexture;
import engine.components.collisionbox.CollisionBox;
import engine.components.entities.Entity;

public class TestCollision1 extends Entity{

	StaticTexture static_texture;
	CollisionBox collision_box;
	
	public TestCollision1(Vector3f position, Vector2f pixel_scale, float rotation) {
		super(position, pixel_scale, rotation);
		static_texture = new StaticTexture("res/sprites/man1.png");
		collision_box = new CollisionBox(this, true, "TestCollision1d");
	}

	@Override
	public int GetTextureId() {
		return static_texture.getTextureId();
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		if(collision_box.Collides("Player")) {
			SetDeltaPosition(new Vector3f(1, 0, 0));
		}
	}

}
