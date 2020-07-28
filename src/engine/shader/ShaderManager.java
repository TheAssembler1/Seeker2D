package engine.shader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import engine.debug.Debug;
import engine.maths.Vector3i;

import static org.lwjgl.opengl.GL32.*;

public class ShaderManager {
	private ShaderManager() {}
	
	private static int FileToString(String file_name, int type){
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file_name));
		} catch (FileNotFoundException e) {
			Debug.LogErr("FileToString() could not find file to read from!");
			e.printStackTrace();
		}
		
		StringBuilder file_string = new StringBuilder();
		String line;
		
		try {
			while((line = br.readLine()) != null) {
				file_string.append(line);
				file_string.append(System.lineSeparator());
			}
		} catch (IOException e) {
			Debug.LogErr("FileToString() error while reading file!");
			e.printStackTrace();
		}
		
		int id = glCreateShader(type);
		
		glShaderSource(id, file_string.toString());
		glCompileShader(id);
		
		if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) 
		    Debug.LogErr("FileToString() could not compile shader!");
		
		return id;
	}
	
	public static Vector3i LoadShader(String vertex_file_name, String frag_file_name) {
		int vertex_id = FileToString(vertex_file_name, GL_VERTEX_SHADER);
		int frag_id=  FileToString(frag_file_name, GL_FRAGMENT_SHADER);
		
		int program_id = glCreateProgram();
		
		glAttachShader(program_id, vertex_id);
		glAttachShader(program_id, frag_id);
		
		glLinkProgram(program_id);
		glValidateProgram(program_id);
		
		Vector3i programs = new Vector3i();
		
		programs.x = vertex_id;
		programs.y = frag_id;
		programs.z = program_id;
		
		return programs;
	}
	
	public static void StartShader(int shader_id) {
		glUseProgram(shader_id);
	}
	
	public static void StopShader() {
		glUseProgram(0);
	}
	
	public static void LoadFloat(String uniform_name, float value, int shader_id) {
		glUniform1f(glGetUniformLocation(shader_id, uniform_name), value);
	}
	
	public static void LoadVector3(String uniform_name, Vector3f vector, int shader_id) {
		glUniform3f(glGetUniformLocation(shader_id, uniform_name), vector.x, vector.y, vector.z);
	}
	
	public static void LoadBoolean(String uniform_name, boolean value, int shader_id) {
		float to_load = 0;
		if(value) 
			to_load = 1;
		glUniform1f(glGetUniformLocation(shader_id, uniform_name), to_load);
	}
	
	public static void LoadMatrix4(String uniform_name, Matrix4f matrix, int shader_id) {
		FloatBuffer matrix_buffer = BufferUtils.createFloatBuffer(4*4);
		matrix.store(matrix_buffer);
		matrix_buffer.flip();
		glUniformMatrix4fv(glGetUniformLocation(shader_id, uniform_name), false, matrix_buffer);
	}
}
