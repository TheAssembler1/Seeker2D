package engine.input;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;

import engine.display.DisplayManager;

public class Input {
	public static int[] keys = new int[GLFW_KEY_LAST];
	public static int[] mouse_buttons = new int[GLFW_MOUSE_BUTTON_LAST];

	private static DoubleBuffer cursor_pos_x = BufferUtils.createDoubleBuffer(1);
	private static DoubleBuffer cursor_pos_y = BufferUtils.createDoubleBuffer(1);
	
	private static float GetCursorPosX() {
		return (float)cursor_pos_x.get(0);
	}
	
	private static float GetCursorPosY() {
		return (float)cursor_pos_y.get(0);
	}
	
	public static Vector2f GetCursorPos() {
		return new Vector2f(GetCursorPosX(), GetCursorPosY());
	}
	
	private static void UpdateMouse() {
		glfwGetCursorPos(DisplayManager.window, cursor_pos_x, cursor_pos_y);
	}
	
	public static void UpdateKeys() {
		glfwSetKeyCallback(DisplayManager.window, (window, key, scancode, action, mods) -> {
			keys[key] = action;
		});
		glfwSetMouseButtonCallback(DisplayManager.window, (window, button, action, mods) -> {
			mouse_buttons[button] = action;
		});
	}

	public static void InputUpdateEvents() {
		for(int i = 0; i < GLFW_KEY_LAST; i++) 
			Input.keys[i] = -1;
		for(int i = 0; i < GLFW_MOUSE_BUTTON_LAST; i++)
			Input.mouse_buttons[i] = -1;
		glfwPollEvents();
		UpdateMouse();
	}
}
