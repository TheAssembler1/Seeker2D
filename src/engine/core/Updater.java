package engine.core;

import static org.lwjgl.glfw.GLFW.*;

import engine.components.entities.Entity;
import engine.display.DisplayManager;
import engine.input.Input;

public class Updater{
	
	private Updater() {}
	
	public static void Update() {
		Input.InputUpdateEvents();
		glfwPollEvents();
		
		for(Entity entity: Looper.entity_renderer) {
			entity.Update();
		}
			
		if(glfwWindowShouldClose(DisplayManager.window))
			Entry.running = false;
	}
}
