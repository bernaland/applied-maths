package com.uelbosque.appliedMaths.figures;

import static org.lwjgl.opengl.GL11.*;

public abstract class ShapeBase {
    public void draw() {
        glPushMatrix();
        {
            buildShape();
        }
        glPopMatrix();
    }

    protected abstract void buildShape();
}
