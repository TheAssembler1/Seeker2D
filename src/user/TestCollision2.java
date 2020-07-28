package user;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.components.animation.StaticTexture;
import engine.components.collisionbox.CollisionBox;
import engine.components.entities.Entity;

public class TestCollision2 extends Entity{

	StaticTexture static_texture;
	CollisionBox collision_box;
	
	public TestCollision2(Vector3f position, Vector2f pixel_scale, float rotation) {
		super(position, pixel_scale, rotation);
		static_texture = new StaticTexture("res/sprites/man2.png");
		collision_box = new CollisionBox(this, true, "TestCollision2");
	}

	@Override
	public int GetTextureId() {
		return static_texture.getTextureId();
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		if(collision_box.Collides("Player")) {
			SetDeltaRotation(1);
		}
	}

}
