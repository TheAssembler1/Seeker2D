package user;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.components.animation.StaticTexture;
import engine.components.collisionbox.CollisionBox;
import engine.components.entities.Entity;
import engine.display.DisplayManager;
import engine.input.Input;

import static org.lwjgl.glfw.GLFW.*;

public class Spaceship extends Entity{
	
	StaticTexture static_texture;
	CollisionBox collision_box;

	public Spaceship(Vector3f position, Vector2f pixel_scale, float rotation) {
		super(position, pixel_scale, rotation);
		static_texture = new StaticTexture("res/sprites/spaceship.png");
		
		collision_box = new CollisionBox(this, true, "Spaceship");
	}

	private boolean left = false;
	private boolean right = false;
	
	private float right_bound = DisplayManager.WINDOW_WIDTH - ((GetPixelScale().x * GetScale().x)/2);
	private float left_bound = ((GetPixelScale().x * GetScale().x)/2);
	
	private int spaceship_speed = 5;
	
	@Override
	public int GetTextureId() {
		return static_texture.getTextureId();
	}
	
	@Override
	public void Update() {
		if(Input.keys[GLFW_KEY_A] == GLFW_PRESS) {left = true; right = false;}
		if(Input.keys[GLFW_KEY_D] == GLFW_PRESS) {right = true; left = false;}
		if(Input.keys[GLFW_KEY_A] == GLFW_RELEASE) left = false;
		if(Input.keys[GLFW_KEY_D] == GLFW_RELEASE) right = false;
		
		if(Input.keys[GLFW_KEY_SPACE] == GLFW_PRESS) {
			new Bullet(new Vector3f(GetPosition().x,GetPosition().y, GetPosition().z), new Vector2f(5, 5), 0);
		}
		
		if(right && GetPosition().x < right_bound) SetDeltaPosition(new Vector2f(spaceship_speed, 0));
		if(left && GetPosition().x > left_bound) SetDeltaPosition(new Vector2f(-spaceship_speed, 0));
	}

}
