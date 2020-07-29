package user;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.components.animation.StaticTexture;
import engine.components.collisionbox.CollisionBox;
import engine.components.entities.Entity;
import engine.input.Input;

public class Player extends Entity{
	
	StaticTexture static_texture;
	CollisionBox collision_box;

	public Player(Vector3f position, Vector2f pixel_scale, float rotation) {
		super(position, pixel_scale, rotation);
		static_texture = new StaticTexture("res/sprites/image.png");
		SetScale(new Vector2f(1, 1));
		
		collision_box = new CollisionBox(this, true, "Player");
	}

	@Override
	public int GetTextureId() {
		return static_texture.getTextureId();
	}

	boolean right = false;
	boolean left = false;
	boolean up = false;
	boolean down = false;
	int speed = 5;
	
	@Override
	public void Update() {
		collision_box.Update();
		
		if(Input.keys[GLFW_KEY_A] == GLFW_PRESS) {
			left = true;
			right = false;
		}else if(Input.keys[GLFW_KEY_D] == GLFW_PRESS) {
			left = false;
			right = true;
		}else if(Input.keys[GLFW_KEY_W] == GLFW_PRESS) {
			up = true;
			down = false;
		}else if(Input.keys[GLFW_KEY_S] == GLFW_PRESS) {
			up = false;
			down = true;
		}
		
		if(Input.keys[GLFW_KEY_A] == GLFW_RELEASE) {
			left = false;
		}else if(Input.keys[GLFW_KEY_D] == GLFW_RELEASE) {
			right = false;
		}
		
		if(Input.keys[GLFW_KEY_W] == GLFW_RELEASE) {
			up = false;
		}else if(Input.keys[GLFW_KEY_S] == GLFW_RELEASE) {
			down = false;
		}
		
		if(right) {
			SetDeltaPosition(new Vector3f(5, 0, 0));
		}else if(left) {
			SetDeltaPosition(new Vector3f(-5, 0, 0));
		}
		
		if(up) {
			SetDeltaPosition(new Vector3f(0, -5, 0));
		}else if(down) {
			SetDeltaPosition(new Vector3f(0, 5, 0));
		}
		
		if(Input.mouse_buttons[GLFW_MOUSE_BUTTON_1] == GLFW_PRESS)
			System.out.println("MOUSE WAS PRESSED");
	}

}
