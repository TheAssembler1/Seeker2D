package engine.components.animation;

import java.util.HashMap;

public class Animation {
	
	private HashMap<String, AnimatedTexture> animated_textures = new HashMap<String, AnimatedTexture>();
	public String current_animated_texture = null;
	
	public void AddAnimatedTexture(AnimatedTexture animated_texture, String animated_texture_id) {
		animated_textures.put(animated_texture_id, animated_texture);
	}
	
	public void SetCurrentAnimatedTexture(String animated_texture_id) {
		current_animated_texture = animated_texture_id;
	}
	
	public void Update() {
		if(current_animated_texture == null)
			return;
		
		AnimatedTexture animated_texture = animated_textures.get(current_animated_texture);
		animated_texture.OnUpdate();
	}
	
	public int GetTextureId() {
		AnimatedTexture animated_texture = animated_textures.get(current_animated_texture);
		return animated_texture.GetTextureId();
	}
}
