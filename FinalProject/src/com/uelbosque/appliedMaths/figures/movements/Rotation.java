package com.uelbosque.appliedMaths.figures.movements;

public class Rotation {
    private float angle;
    private float x;
    private float y;
    private float z;

    public Rotation() {
        this(0, 0, 0, 0);
    }

    public Rotation(float a, float x, float y, float z) {
        angle = a;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
