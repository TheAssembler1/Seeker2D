package engine.components.entities;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import engine.buffers.BufferManager;

import static org.lwjgl.opengl.GL32.*;

public abstract class RawEntity {
	
	private Vector2f scale = new Vector2f(1, 1);
	private Vector2f pixel_scale;
	private float rotation;
	private Vector3f position;
	
	private int vao_id;
	
	private float[] vertices;
		
	private static int[] indices = {
		0, 1, 2, 
		2, 1, 3
	};
		
	private float[] texture_coords = {
		0, 1,
		0, 0,
		1, 1,
		1, 0
	};
	
	public RawEntity(Vector3f position, Vector2f pixel_scale, float rotation) {
		this.position = position;
		this.rotation = rotation;
		this.pixel_scale = pixel_scale;
		
		vertices = CreateVertices(new Vector3f(0, 0, 0), pixel_scale);
		
		vao_id = BufferManager.CreateVao();
		BufferManager.CreateVbo(vao_id, 3, vertices, 0, GL_FLOAT, 0, 0);
		BufferManager.CreateVbo(vao_id, 2, texture_coords, 1, GL_FLOAT, 0, 0);
		BufferManager.CreateEbo(vao_id, indices);
	}
	
	private float[] CreateVertices(Vector3f position, Vector2f scale){	
		float[] vertices = {
			position.x - (scale.x / 2), position.y + (scale.y / 2), position.z,
			position.x - (scale.x / 2), position.y - (scale.y / 2), position.z,
			
			position.x + (scale.x / 2), position.y + (scale.y / 2), position.z,
			position.x + (scale.x / 2), position.y - (scale.y / 2), position.z
		};
		
		return vertices;
	}
	
	public int GetVaoId() {
		return vao_id;
	}
	
	public int GetIndicesLength() {
		return indices.length;
	}
	
	public void SetDeltaScale(Vector2f delta_scale) {
		this.scale.x += delta_scale.x;
		this.scale.y += delta_scale.y;
	}
	
	public void SetDeltaPosition(Vector3f delta_position) {
		this.position.x += delta_position.x;
		this.position.y += delta_position.y;
		this.position.z += delta_position.z;
	}
	
	public void SetDeltaRotation(int delta_rotation) {
		this.rotation += delta_rotation;
	}
	
	public void SetScale(Vector2f scale) {
		this.scale = scale;
	}
	
	public void SetPosition(Vector3f position) {
		this.position = position;
	}
	
	public void SetRotation(float f) {
		this.rotation = f;
	}
	
	public Vector2f GetScale() {
		return scale;
	}
	
	public Vector2f GetPixelScale() {
		return pixel_scale;
	}
	
	public Vector3f GetPosition() {
		return position;
	}
	
	public float GetRotation() {
		return rotation;
	}
}
