package com.uelbosque.appliedMaths;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class App {
    public static void main(String[] args) throws Exception {
        mainWindow();
        initGL();
        mainGraphs();
        clean();
    }

    private static void initGL() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glDisable(GL_DEPTH_TEST);
        glClearColor(0, 0, 0, 0);
    }

    private static void mainWindow() {
        try {
            Display.setDisplayMode(new DisplayMode(600, 600));
            Display.create();
            Display.setTitle("Applied Maths Project");
            Keyboard.create();
            Mouse.create();
            Display.setVSyncEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void graph() {
		//se limpia el Buffer con el color asignado anteriormente
		glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();	
        //Actualiza el despliegue de la vista
        
                
		Display.update();
        Display.sync(60);	
	}

    private static void mainGraphs() {
        while (!Display.isCloseRequested()) {
            getKey();
            graph();
        }
    }

    private static void clean() {
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }

    private static void getKey(){
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState()) {
                
            }
        }
    }
}
