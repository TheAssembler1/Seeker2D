package user;

import org.lwjgl.util.vector.Vector3f;

public final class Window {
	private Window() {}
	
	//width and height of window
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 500;
	
	//values range from 1 >= 0
	public static final Vector3f WINDOW_BACKGROUND_COLOR = new Vector3f(1, 0, 0);
	
	//rate of the UPDATE loop 
	//note rendering loop is not capped
	public static final int FPS = 60;
}
