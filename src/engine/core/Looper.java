package engine.core;

import java.util.ArrayList;
import java.util.List;

import engine.components.entities.Entity;
import engine.display.DisplayManager;
import engine.render.Renderer;
import engine.texture.TextureManager;
import user.Scene;
import user.settings.General;
import user.settings.Window;

public class Looper implements Runnable{
	
	public static List<List<Entity>> entity_renderer = new ArrayList<List<Entity>>(0);
	
	public static List<Entity> add_to_entity_renderer = new ArrayList<Entity>();
	public static List<Entity> remove_from_entity_renderer = new ArrayList<Entity>();
	
	public static void Loop() {
		try {
			DisplayManager.Load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		final int FPS = Window.FPS;
		long last_time = System.currentTimeMillis();
		int delta = 0;
		
		Scene scene = new Scene();
		scene.Start();
		
		Renderer.RendererSetup();
		
		//adding layers to the renderer
		for(int i = 0; i < General.LAYERS; i++) {
			List<Entity> entity_list = new ArrayList<Entity>();
			entity_renderer.add(entity_list);
		}
		
		while(Entry.running) {
			
			delta += System.currentTimeMillis() - last_time;
			last_time = System.currentTimeMillis();
			
			if(delta > 1000/FPS) {
				Updater.Update();
				delta = 0;
			}

			for(Entity entity: add_to_entity_renderer) {
				List<Entity> entity_list = entity_renderer.get((int)entity.GetPosition().z - 1);
				entity_list.add(entity);
			}
			
			for(Entity entity: remove_from_entity_renderer) {
				List<Entity> entity_list = entity_renderer.get((int)entity.GetPosition().z - 1);
				entity_list.remove(entity);
			}
			
			add_to_entity_renderer.clear();
			remove_from_entity_renderer.clear();
			
			Renderer.Render();
			
			DisplayManager.Update();
			DisplayManager.EndFrame();
		}
		
		Renderer.RendererCleanup();
		DisplayManager.Unload();
		TextureManager.UnloadTextures();
	}

	@Override
	public void run() {	
		Loop();
	}
}
