package com.uelbosque.appliedMaths;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.uelbosque.appliedMaths.figures.Cone;
import com.uelbosque.appliedMaths.figures.ShapeBase;
import com.uelbosque.appliedMaths.figures.Sphere;
import com.uelbosque.appliedMaths.figures.movements.Rotation;
import com.uelbosque.appliedMaths.figures.movements.Translation;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class App {
    private static int x = 0;
    private static int y = 0;
    private static int z = 0;

    private static float rotation = 0;
    private static float xPos = 0;
    private static float zPos = -10;

    private static final ArrayList<ShapeBase> shapes = new ArrayList<ShapeBase>();

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

        // shapes.add(new Sphere(1));
        ShapeBase left = new Cone(4, 5, 5),
            center = new Cone(4, 5, 5),
            right = new Cone(4, 5, 5);

        Rotation rLeft = new Rotation(-45, 1, 1, 0),
            rCenter = new Rotation(45, 1, 0, 0),
            rRight = new Rotation(45, 1, 1, 0);

        Translation tLeft = new Translation(-3, 0, 0),
            tRight = new Translation(3, 0, 0);

        left.setRotation(rLeft);
        center.setRotation(rCenter);
        right.setRotation(rRight);
        left.setTranslation(tLeft);
        right.setTranslation(tRight);

        shapes.add(left);
        shapes.add(center);
        shapes.add(right);
    }

    private static ByteBuffer getByteBufferFromString(){

        String text = "ABCD";
        int s = 256; //Take whatever size suits you.
        BufferedImage b = new BufferedImage(s, s, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = b.createGraphics();
        g.drawString(text, 0, 0);

        int co = b.getColorModel().getNumComponents();

        byte[] data = new byte[co * s * s];
        b.getRaster().getDataElements(0, 0, s, s, data);

        ByteBuffer pixels = BufferUtils.createByteBuffer(data.length);
        pixels.put(data);
        pixels.rewind();
        return pixels;
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
        // glPushMatrix();
        // {
            
        //     glRotated(60, 1, 0, 0);
        //     glRotated(60, 0, 1, 0);
        // Translation trans = new Translation();
        // trans.setzCoor(-10);
        // ball.setTranslation(trans);
        // ball.draw();
        for (ShapeBase shape : shapes) {
            shape.draw();
        }
        
        glEnd();
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
