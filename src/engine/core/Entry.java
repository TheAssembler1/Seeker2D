package engine.core;

import java.io.IOException;

public class Entry {
	
	public static boolean running = true;
	
	public static void main(String[] args) throws IOException{
		Thread renderer_thread = new Thread(new Looper(), "renderer_thread");
		renderer_thread.start();
	}
}
