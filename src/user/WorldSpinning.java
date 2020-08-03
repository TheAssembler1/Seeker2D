package user;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.components.animation.AnimatedTexture;
import engine.components.animation.Animation;
import engine.components.entities.Entity;
import engine.texture.TextureManager;

public class WorldSpinning extends Entity{

	AnimatedTexture animated_texture;
	Animation animation;
	
	@SuppressWarnings("serial")
	public WorldSpinning(Vector3f position, Vector2f pixel_scale, float rotation) {
		super(position, pixel_scale, rotation);
	
		int number_of_frames = 43;
		
		animated_texture = new AnimatedTexture(TextureManager.TextureList(new ArrayList<String>() {{
			for(int i = 0; i <= number_of_frames; i++) {
				add("res/sprites/worldspinning/worldspinning-" + i + ".png");
			}
		}}), 12);

		animation = new Animation();
		animation.AddAnimatedTexture(animated_texture, "Spinning");
		animation.SetCurrentAnimatedTexture("Spinning");
	}

	@Override
	public int GetTextureId() {
		return animation.GetTextureId();
	}

	@Override
	public void Update() {
		animation.Update();
	}

}
