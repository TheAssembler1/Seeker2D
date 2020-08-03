package engine.core;

import static org.lwjgl.glfw.GLFW.*;

import java.util.List;

import engine.components.entities.Entity;
import engine.display.DisplayManager;
import engine.input.Input;

public class Updater{
	
	private Updater() {}
	
	public static void Update() {
		Input.InputUpdateEvents();
		
		for(List<Entity> entity_list: Looper.entity_renderer) {
			for(Entity entity: entity_list)
				entity.Update();
		}
			
		if(glfwWindowShouldClose(DisplayManager.window))
			Entry.running = false;
	}
}
