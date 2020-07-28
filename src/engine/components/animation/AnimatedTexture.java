package engine.components.animation;

import java.util.ArrayList;

public class AnimatedTexture {
	
	ArrayList<Integer> texture_list = new ArrayList<Integer>();
	
	private int current_texture_index = 0;
	private int texture_id;
	private int animation_speed;
	private int stored_animation_speed;
	
	public AnimatedTexture(ArrayList<Integer> texture_list, int animation_speed) {
		this.texture_list = texture_list;
		this.animation_speed = animation_speed;
		stored_animation_speed = animation_speed;
	}
	
	public void OnUpdate() {
		texture_id = texture_list.get(current_texture_index);	
		
		animation_speed += stored_animation_speed;
		
		if(animation_speed >= 60) {
			current_texture_index++;
			animation_speed = 0;
		}
		
		if(current_texture_index > texture_list.size()-1)
			current_texture_index = 0;
	}
	
	public int GetTextureId() {
		return texture_id;
	}
}
