package engine.input;

import static org.lwjgl.glfw.GLFW.*;

import engine.display.DisplayManager;

public class Input {
	public static int[] keys = new int[GLFW_KEY_LAST];
	
	public static void UpdateKeys() {
		glfwSetKeyCallback(DisplayManager.window, (window, key, scancode, action, mods) -> {
			keys[key] = action;
		});
	}

	public static void InputUpdateEvents() {
		for(int i = 0; i < GLFW_KEY_LAST; i++) {
			Input.keys[i] = -1;
		}
	}
}
