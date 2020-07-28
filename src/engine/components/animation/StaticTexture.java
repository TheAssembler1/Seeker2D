package engine.components.animation;

import engine.texture.TextureManager;

public class StaticTexture {
	
	private int texture_id;
	
	public StaticTexture(String texture_file_name) {
		texture_id = TextureManager.LoadTexture(texture_file_name); 
	}
	
	public int getTextureId() {
		return texture_id;
	}
}
