package engine.render;

import static org.lwjgl.opengl.GL32.*;

import engine.components.entities.Entity;
import engine.core.Looper;
import engine.display.DisplayManager;
import engine.maths.Matrices;
import engine.maths.Vector3i;
import engine.shader.ShaderManager;
import engine.texture.TextureManager;

public class Renderer {
	private Renderer() {}
	
	private static int SHADER_ID;
	private static int VERT_ID;
	private static int FRAGMENT_ID;
	
	private static Vector3i programs;
	
	public static void RendererSetup() {
		programs = ShaderManager.LoadShader("src/engine/shader/ShaderVert.txt", "src/engine/shader/ShaderFrag.txt");
		
		VERT_ID = (int)programs.x;
		FRAGMENT_ID = (int)programs.y;
		SHADER_ID = (int)programs.z;
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void RendererCleanup() {
		glDetachShader(SHADER_ID, VERT_ID);
		glDetachShader(SHADER_ID, FRAGMENT_ID);
		glDeleteShader(VERT_ID);
		glDeleteShader(FRAGMENT_ID);
		glDeleteProgram(SHADER_ID);
	}
	
	public static void AddEntity(Entity entity) {
		Looper.entity_renderer.add(entity);
	}
	
	private static void RendererPrepare() {
        glClearColor(DisplayManager.im_gui_ui.background_color[0], DisplayManager.im_gui_ui.background_color[1], DisplayManager.im_gui_ui.background_color[2], 0.0f);
        glClear(GL_COLOR_BUFFER_BIT);
	}
	
	private static void RenderEntity(Entity entity) {
		ShaderManager.StartShader(SHADER_ID);
		
		glBindVertexArray(entity.GetVaoId());
		
		ShaderManager.LoadMatrix4("trans_mat", Matrices.CreateTransformationmatrix(entity.GetPosition(), entity.GetScale(), entity.GetRotation()), SHADER_ID);
		ShaderManager.LoadMatrix4("ortho_proj_mat", Matrices.CreateViewMatrix(0, DisplayManager.WINDOW_WIDTH, 0, DisplayManager.WINDOW_HEIGHT, 5, -5), SHADER_ID);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
	
		TextureManager.StartTexture(GL_TEXTURE0, GL_TEXTURE_2D, entity.GetTextureId());
		
		glDrawElements(GL_TRIANGLES, entity.GetIndicesLength(), GL_UNSIGNED_INT, 0);
		
		TextureManager.StopTexture(GL_TEXTURE_2D);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		
		glBindVertexArray(0);
		
		ShaderManager.StopShader();
	}
	
	public static void Render() {
		RendererPrepare();
		for(Entity entity: Looper.entity_renderer) {
			RenderEntity(entity);
		}
	}
}
