package engine.display;

import imgui.type.ImBoolean;
import user.settings.Window;
import imgui.ImGui;
import imgui.flag.ImGuiColorEditFlags;
import imgui.flag.ImGuiCond;

public final class ImGuiUi {
	
	public final float[] background_color = new float[]{Window.WINDOW_BACKGROUND_COLOR.x, Window.WINDOW_BACKGROUND_COLOR.y, Window.WINDOW_BACKGROUND_COLOR.z};
 
    private final ImBoolean show_demo_window = new ImBoolean();
    
    private boolean enable_wireframe_mode = false;
    private boolean disable_wireframe_mode = true;
    
    private final int WINDOW_WIDTH = 200;
    private final int WINDOW_HEIGHT = 130;

    void render() {
        ImGui.setNextWindowSize(WINDOW_WIDTH, WINDOW_HEIGHT, ImGuiCond.Once);
        ImGui.setNextWindowPos(10, 10, ImGuiCond.Once);

        ImGui.begin("Custom window");
        ImGui.checkbox("Show demo window", show_demo_window);
        
        if(ImGui.radioButton("Enable Wireframe Mode", enable_wireframe_mode)) {
        	DisplayManager.EnableWireframeMode();
        	disable_wireframe_mode = false;
        	enable_wireframe_mode = true;
        }
        if(ImGui.radioButton("Disable Wireframe Mode", disable_wireframe_mode)) {
        	DisplayManager.DisableWireframeMode();  
        	enable_wireframe_mode = false;
        	disable_wireframe_mode = true;
        }
        
        ImGui.separator();

        ImGui.alignTextToFramePadding();
        ImGui.text("Background color:");
        ImGui.sameLine();
        ImGui.colorEdit3("##click_counter_col", background_color, ImGuiColorEditFlags.NoInputs | ImGuiColorEditFlags.NoDragDrop);

        ImGui.end(); 

        if (show_demo_window.get()) {
            ImGui.showDemoWindow(show_demo_window);
        }
    
    }
}