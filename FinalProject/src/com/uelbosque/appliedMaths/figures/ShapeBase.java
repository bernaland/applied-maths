package com.uelbosque.appliedMaths.figures;

import static org.lwjgl.opengl.GL11.*;

import com.uelbosque.appliedMaths.figures.movements.Rotation;
import com.uelbosque.appliedMaths.figures.movements.Translation;

public abstract class ShapeBase {
    protected Translation translation = new Translation();
    protected Rotation rotation = new Rotation();

    public void draw() {
        glPushMatrix();
        {
            buildShape();
        }
        glPopMatrix();
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    protected abstract void buildShape();
}
