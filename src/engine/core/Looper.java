package engine.core;

import java.util.ArrayList;
import java.util.List;

import engine.components.entities.Entity;
import engine.display.DisplayManager;
import engine.render.Renderer;
import engine.texture.TextureManager;
import user.Scene;
import user.Window;

public class Looper implements Runnable{
	
	public static List<Entity> entity_renderer = new ArrayList<Entity>();
	
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
		
		while(Entry.running) {
			delta += System.currentTimeMillis() - last_time;
			last_time = System.currentTimeMillis();
			
			if(delta > 1000/FPS) {
				Updater.Update();
				delta = 0;
			}
			
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
