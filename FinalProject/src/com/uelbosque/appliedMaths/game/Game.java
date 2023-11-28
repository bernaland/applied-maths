package com.uelbosque.appliedMaths.game;

import static org.lwjgl.util.glu.GLU.gluLookAt;

import java.util.ArrayList;
import java.util.Random;

import com.uelbosque.appliedMaths.figures.Cone;
import com.uelbosque.appliedMaths.figures.ShapeBase;
import com.uelbosque.appliedMaths.figures.Sphere;
import com.uelbosque.appliedMaths.figures.CircleVertex;
import com.uelbosque.appliedMaths.figures.movements.Rotation;
import com.uelbosque.appliedMaths.figures.movements.Translation;

public class Game {
    private final ArrayList<ShapeBase> shapes; 
    private int ballPosition = 0;
    private final Random randGen = new Random(System.currentTimeMillis());
    private double angle = 0;
    private int suffles = -1;

    public Game() {
        shapes = new ArrayList<ShapeBase>();
    }

    public void initialize() {
        shapes.clear();

        Cone left = new Cone(4, 5, 5),
            center = new Cone(4, 5, 5),
            right = new Cone(4, 5, 5);

        Translation tLeft = new Translation(-3, 1, 0),
            tRight = new Translation(3, 1, 0),
            tCenter = new Translation(0, 1, 0);
        Rotation basicRotation = new Rotation(90, 1, 0, 0);

        left.setTranslation(tLeft);
        center.setTranslation(tCenter);
        right.setTranslation(tRight);
        left.setRotation(basicRotation);
        center.setRotation(basicRotation);
        right.setRotation(basicRotation);

        shapes.add(left);
        shapes.add(center);
        shapes.add(right);
    }

    public void moveBall() {
        ballPosition = randGen.nextInt(3);

        showBall();
    }

    public void showBall() {
        Sphere ball = new Sphere(0.3f);
        int axisTranslation = 0;

        if (ballPosition == 0) {
            axisTranslation = -3;
        } else if (ballPosition == 2) {
            axisTranslation = 3;
        }

        Translation translation = new Translation(axisTranslation, 0, 0);
        ball.setTranslation(translation);

        shapes.add(ball);

        Rotation rotation = new Rotation(45, 1, 1, 0);
        shapes.get(ballPosition).setRotation(rotation);
    }

    public void suffleBall() {
        ballPosition = randGen.nextInt(3);
        if (shapes.size() > 3) {
            shapes.remove(3);
        }
        suffles = 0;
    }

    public void draw() {
        if (suffles >= 0 && suffles < 5) {
            ShapeBase leftShape = suffles % 2 == 0 ? shapes.get(0) : shapes.get(1),
                rightShape = suffles % 2 == 0 ? shapes.get(1) : shapes.get(2);
            float left = suffles % 2 == 0 ? -1.5f : 1.5f,
                right = suffles % 2 == 0 ? -1.5f : 1.5f;
            
            Translation lTranslation = leftShape.getTranslation();
            CircleVertex lVertex = new CircleVertex(angle, -90, 1);
            lTranslation.setxCoor((float)lVertex.getX()*1.5f + left);
            lTranslation.setyCoor((float)lVertex.getY()*1.5f);
            lTranslation.setzCoor((float)lVertex.getZ()*1.5f);

            Translation rTranslation = rightShape.getTranslation();
            CircleVertex rVertex = new CircleVertex(90 - angle, -90, 1);
            rTranslation.setxCoor((float)rVertex.getX()*1.5f + right);
            rTranslation.setyCoor((float)rVertex.getY()*1.5f);
            rTranslation.setzCoor((float)rVertex.getZ()*1.5f);
        }
        if (suffles == 5) {
            suffles = -1;
            angle = 0;
            shapes.get(0).setTranslation(new Translation(-3, 0, 0));
            shapes.get(1).setTranslation(new Translation(0, 0, 0));
            shapes.get(2).setTranslation(new Translation(3, 0, 0));
        }
        if (angle < -Math.PI && suffles >= 0) {
            angle = 0;
            suffles++;
        }
        for (ShapeBase shape : shapes) {
            shape.draw();
        }
        angle-=Math.PI/30;
    }
}
