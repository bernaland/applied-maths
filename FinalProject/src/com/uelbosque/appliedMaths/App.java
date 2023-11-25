package com.uelbosque.appliedMaths;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.uelbosque.appliedMaths.figures.Sphere;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class App {
    private static int x = 0;
    private static int y = 0;
    private static int z = 0;

    private static float rotation = 0;
    private static float xPos = 0;
    private static float zPos = -10;

    private static Sphere ball;

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

        ball = new Sphere(1);
    }

    private static void mainWindow() {
        try {
            Display.setDisplayMode(new DisplayMode(1000, 800));
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
        // glPushMatrix();
        // {
            
        //     glRotated(60, 1, 0, 0);
        //     glRotated(60, 0, 1, 0);
        
            ball.draw();
        // }
        // glPopMatrix();
        // drawShape();
                
		Display.update();
        Display.sync(60);	
	}

    private static void mainGraphs() {
        while (!Display.isCloseRequested()) {
            getKey();
            graph();
            rotation+=0.5;
        }
    }

    private static void drawShape() {
        glPushMatrix();
        {
            glTranslatef(xPos, 0, zPos);
            glRotatef(rotation, 1, 0, 0 );
            
            glBegin(GL_QUADS);
			{
				//Frente de color rojo
				glColor3f(1f, 0f, 0f);
				glVertex3f(-1, -1, 1);
				glVertex3f(-1, 1, 1);
				glVertex3f(1, 1, 1);
				glVertex3f(1, -1, 1);
				
				//Fondo de color verde
				glColor3f(0f, 1f, 0f);
				glVertex3f(-1, -1, -1);
				glVertex3f(-1, 1, -1);
				glVertex3f(1, 1, -1);
				glVertex3f(1, -1, -1);
				
				//Lado lateral izquierdo azul
				glColor3f(0f, 0f, 1f);
				glVertex3f(-1, -1, -1);
				glVertex3f(-1, -1, 1);
				glVertex3f(-1, 1, 1);
				glVertex3f(-1, 1, -1);
				
				//Lado lateral derecho amarillo
				glColor3f(1f, 1f, 0f);
				glVertex3f(1, -1, -1);
				glVertex3f(1, -1, 1);
				glVertex3f(1, 1, 1);
				glVertex3f(1, 1, -1);
				
				//Parte inferior de color celeste
				glColor3f(0f, 1f, 1f);
				glVertex3f(-1, -1, -1);
				glVertex3f(1, -1, -1);
				glVertex3f(1, -1, 1);
				glVertex3f(-1, -1, 1);
				
				//Parte superior de color fucsia 
				glColor3f(1f, 0f, 1f);
				glVertex3f(-1, 1, -1);
				glVertex3f(1, 1, -1);
				glVertex3f(1, 1, 1);
				glVertex3f(-1, 1, 1);
				
			}
            glEnd();
        }
        glPopMatrix();
    }

    private static void clean() {
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }

    private static void getKey(){
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            x++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            y++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            z++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            x--;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            y--;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            z--;
        }
    }
}
