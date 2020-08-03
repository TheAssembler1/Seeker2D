package user;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.display.DisplayManager;

public class Scene {
	public void Start() {
		new Spaceship(new Vector3f(DisplayManager.WINDOW_WIDTH/2, DisplayManager.WINDOW_HEIGHT - 50, 2), new Vector2f(50, 50), 0);
		new WorldSpinning(new Vector3f(DisplayManager.WINDOW_WIDTH - 40, DisplayManager.WINDOW_HEIGHT - 30, 3), new Vector2f(50, 50), 0);
		new Background(new Vector3f(DisplayManager.WINDOW_WIDTH/2, -(DisplayManager.WINDOW_HEIGHT/2), 1), new Vector2f(DisplayManager.WINDOW_WIDTH, DisplayManager.WINDOW_HEIGHT), 0);
		new Background(new Vector3f(DisplayManager.WINDOW_WIDTH/2, DisplayManager.WINDOW_HEIGHT/2, 1), new Vector2f(DisplayManager.WINDOW_WIDTH, DisplayManager.WINDOW_HEIGHT), 0);
	}
}
