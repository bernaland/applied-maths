package com.uelbosque.appliedMaths;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.uelbosque.appliedMaths.figures.Cup;
import com.uelbosque.appliedMaths.figures.ShapeBase;
import com.uelbosque.appliedMaths.figures.Sphere;
import com.uelbosque.appliedMaths.figures.movements.Rotation;
import com.uelbosque.appliedMaths.figures.movements.Translation;
import com.uelbosque.appliedMaths.game.Direction;
import com.uelbosque.appliedMaths.game.Game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Box;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class App {
    private static final Game currentGame = new Game();

    public static void main(String[] args) throws Exception {
        mainWindow();
        initGL();
        mainGraphs();
        clean();
    }

    private static void initGL() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        gluPerspective(70, (float)Display.getWidth()/Display.getHeight(), 0.3f, 1000);
        glMatrixMode(GL_MODELVIEW);
        
        glDisable(GL_DEPTH_TEST);
        glClearColor(0, 0, 0, 0);

        currentGame.initialize();
    }

    private static void mainWindow() {
        try {
            Display.setDisplayMode(new DisplayMode(1200, 900));
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
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();	
        //Actualiza el despliegue de la vista
        
        currentGame.draw();
                
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
                currentGame.moveBall();
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()) {
                currentGame.suffleBall();
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
                currentGame.moveSelector(Direction.Left);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                currentGame.moveSelector(Direction.Right);
            }
        }
    }
}
