package engine.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import engine.debug.Debug;

import static org.lwjgl.opengl.GL32.*;

public class TextureManager {
	
	private static ArrayList<Integer> textures = new ArrayList<Integer>();
	
	private static final int BYTES_PER_PIXEL = 4;
	
	public static void StartTexture(int texture_bank, int texture_type, int texture_id) {
		glActiveTexture(texture_bank);
		glBindTexture(texture_type, texture_id);
	}
	
	public static void StopTexture(int texture_type) {
		glBindTexture(texture_type, 0);
	}
	
	public static ArrayList<Integer> TextureList(List<String> texture_files){
		ArrayList<Integer> texture_list = new ArrayList<Integer>();
		
		for(String texture_file: texture_files)
			texture_list.add(LoadTexture(texture_file));
		
		return texture_list;
	}
	
	public static int LoadTexture(String texture_file_name) {
		File texture_file = new File(texture_file_name);
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(texture_file);
		} catch (IOException e) {
			Debug.LogErr("LoadTexture() unable to load texture file!");
			e.printStackTrace();
		}
		
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL);
		
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) {
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));
				buffer.put((byte) ((pixel >> 8) & 0xFF));
				buffer.put((byte) (pixel & 0xFF));
				buffer.put((byte) ((pixel >> 24) & 0xFF));
			}
		}
		
		buffer.flip();

		int texture_id = glGenTextures();
		textures.add(texture_id);
		
		glBindTexture(GL_TEXTURE_2D, texture_id);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		return texture_id;
	}
	
	public static void UnloadTextures() {
		for(int texture: textures)
			glDeleteTextures(texture);
	}
}
