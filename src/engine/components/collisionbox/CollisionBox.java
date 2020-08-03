package engine.components.collisionbox;

import engine.components.animation.StaticTexture;
import engine.components.entities.Entity;
import engine.render.Renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class CollisionBox extends Entity{
	
	private static HashMap<String, List<CollisionBox>> collision_boxes = new HashMap<String, List<CollisionBox>>();
	
	private StaticTexture enabled;
	private StaticTexture disabled;
	private boolean show;
	private Entity entity;
	
	private String collision_box_id;

	public CollisionBox(Entity entity, boolean show, String collision_box_id) {
		super(new Vector3f(entity.GetPosition().x, entity.GetPosition().y, 5), entity.GetPixelScale(), entity.GetRotation());
		this.entity = entity;
		this.show = show;
		this.collision_box_id = collision_box_id;

		enabled = new StaticTexture("engineres/collision_box_enabled.png");
		disabled = new StaticTexture("engineres/collision_box_disabled.png");
		
		AddCollisionBox(this);
	}
	
	private static void AddCollisionBox(CollisionBox collision_box) {
		if(collision_boxes.containsKey(collision_box.GetCollisionBoxId())) {
			List<CollisionBox> batch = collision_boxes.get(collision_box.GetCollisionBoxId());
			batch.add(collision_box);
		}else {
			List<CollisionBox> batch = new ArrayList<CollisionBox>();
			batch.add(collision_box);
			collision_boxes.put(collision_box.GetCollisionBoxId(), batch);
		}
	}
	
	public boolean Collides(String collision_box_id) {
		List<CollisionBox> batch = collision_boxes.get(collision_box_id);
		
		boolean collided = false;
		
		for(CollisionBox collision_box: batch) {
			Vector3f other_position = collision_box.GetPosition();
			Vector2f other_pixel_scale = collision_box.GetPixelScale();
			Vector2f other_scale = collision_box.GetScale();
			
			Vector2f other_final_scale = new Vector2f(other_pixel_scale.x * other_scale.x, other_pixel_scale.y * other_scale.y);
			Vector2f final_scale = new Vector2f(GetPixelScale().x * GetScale().x, GetPixelScale().y * GetScale().y);
			
			float other_left_bound = other_position.x - (other_final_scale.x/2);
			float other_right_bound = other_position.x + (other_final_scale.x/2);
			float other_up_bound = other_position.y - (other_final_scale.y/2);
			float other_down_bound = other_position.y + (other_final_scale.y/2);
			
			float left_bound = GetPosition().x - (final_scale.x/2);
			float right_bound = GetPosition().x + (final_scale.x/2);
			float up_bound = GetPosition().y - (final_scale.y/2);
			float down_bound = GetPosition().y + (final_scale.y/2);
			
			if((left_bound <= other_left_bound && right_bound >= other_left_bound) || (right_bound >= other_right_bound && left_bound <= other_right_bound)) {
				if((up_bound <= other_up_bound && down_bound >= other_up_bound) || (down_bound >= other_down_bound && up_bound <= other_down_bound)) {
					collided = true;
				}
			}
		}
		
		return collided;
	}
	
	public void Show() {
		show = true;
	}
	
	public void Hide() {
		show = false;
	}
	
	private String GetCollisionBoxId() {
		return collision_box_id;
	}

	@Override
	public int GetTextureId() {
		if(show)
			return enabled.getTextureId();
		else
			return disabled.getTextureId();
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		SetPosition(new Vector2f(entity.GetPosition().x, entity.GetPosition().y));
		SetRotation(entity.GetRotation());
		SetScale(entity.GetScale());
	}

}
