package engine.components.entities;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.render.Renderer;

public abstract class Entity extends RawEntity{

	public Entity(Vector3f position, Vector2f pixel_scale, float rotation) {
		super(position, pixel_scale, rotation);
		Renderer.AddEntity(this);
	}

	public abstract int GetTextureId();

	public abstract void Update();
}
