package user;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.display.DisplayManager;

public class Scene {
	public void Start() {
		new Player(new Vector3f(DisplayManager.WINDOW_WIDTH/2, DisplayManager.WINDOW_HEIGHT/2, 0), new Vector2f(100, 100), 0);
		new TestCollision1(new Vector3f(DisplayManager.WINDOW_WIDTH/2 - 300, DisplayManager.WINDOW_HEIGHT/2, 0), new Vector2f(100, 100), 0);
		new TestCollision2(new Vector3f(DisplayManager.WINDOW_WIDTH/2 + 300, DisplayManager.WINDOW_HEIGHT/2, 0), new Vector2f(100, 100), 0);
	}
}
