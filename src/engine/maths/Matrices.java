package engine.maths;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Matrices {
	private Matrices() {}
	
	private static Vector3f z_axis = new Vector3f(0, 0, 1);
	
	public static Matrix4f CreateViewMatrix(float LEFT, float RIGHT, float TOP, float BOTTOM, float FAR, float NEAR) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		
		matrix.m00 = 2 / (RIGHT - LEFT);
		matrix.m11 = 2 / (TOP - BOTTOM);
		matrix.m22 = 2 / (NEAR - FAR);
		matrix.m30 = (RIGHT + LEFT) / (LEFT - RIGHT);
		matrix.m31 = (TOP + BOTTOM) / (BOTTOM - TOP);
		matrix.m32 = (FAR + NEAR) / (FAR - NEAR);
		
		return matrix;
	}
	
	public static Matrix4f CreateTransformationmatrix(Vector3f position, Vector2f scale, float rotation) {
		Matrix4f transformation = new Matrix4f();
		transformation.setIdentity();
		
		transformation.translate(position, transformation);
		transformation.rotate((float) Math.toRadians(rotation), z_axis, transformation);
		transformation.scale(new Vector3f(scale.x, scale.y, 0.0f));
		
		return transformation;
	}
}
