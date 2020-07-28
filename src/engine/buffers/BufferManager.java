package engine.buffers;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL32.*;

public class BufferManager {
	private BufferManager() {}
	
	private static ArrayList<Integer> vaos = new ArrayList<Integer>();
	private static ArrayList<Integer> vbos = new ArrayList<Integer>();
	
	public static int CreateVao() {
		int vao_id = glGenVertexArrays();
		vaos.add(vao_id);
		return vao_id;
	}
	
	public static int CreateVbo(int vao_id, int data_size, float[] data, int attribute_pointer, int data_type, int stride, int offset) {
		int vbo_id = glGenBuffers();
		vbos.add(vbo_id);
		
		glBindVertexArray(vao_id);
		glBindBuffer(GL_ARRAY_BUFFER, vbo_id);
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glVertexAttribPointer(attribute_pointer, data_size, data_type, false, stride, offset);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
		
		return vbo_id;
	}
	
	public static void CreateEbo(int vao_id, int[] indices) {
		int ebo_id = glGenBuffers();
		vbos.add(ebo_id);
		glBindVertexArray(vao_id);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo_id);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
		glBindVertexArray(0);
	}
	
	public static void UnloadBuffers() {
		for(int vao: vaos)
			glDeleteVertexArrays(vao);
		for(int vbo: vbos)
			glDeleteBuffers(vbo);
	}
}
