package user;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.components.animation.StaticTexture;
import engine.components.entities.Entity;
import engine.display.DisplayManager;

public class Background extends Entity{

	StaticTexture static_texture;
	
	public Background(Vector3f position, Vector2f pixel_scale, float rotation) {
		super(position, pixel_scale, rotation);
		static_texture = new StaticTexture("res/sprites/background.png");
	}

	@Override
	public int GetTextureId() {
		return static_texture.getTextureId();
	}

	private int background_speed = 1;
	
	@Override
	public void Update() {
		SetDeltaPosition(new Vector2f(0, background_speed));
		if(GetPosition().y > DisplayManager.WINDOW_HEIGHT + ((GetPixelScale().y * GetScale().y)/2)) 
			SetPosition(new Vector2f(DisplayManager.WINDOW_WIDTH/2, -DisplayManager.WINDOW_HEIGHT/2 + 10));
	}

}
