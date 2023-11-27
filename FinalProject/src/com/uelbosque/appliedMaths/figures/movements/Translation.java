package com.uelbosque.appliedMaths.figures.movements;

public class Translation {
    private float xCoor = 0;
    private float yCoor = 0;
    private float zCoor = 0;

    public Translation() {
        this(0, 0, 0);
    }

    public Translation(float x, float y, float z) {
        xCoor = x;
        yCoor = y;
        zCoor = z;
    }

    public float getxCoor() {
        return xCoor;
    }
    public void setxCoor(float xCoor) {
        this.xCoor = xCoor;
    }
    public float getyCoor() {
        return yCoor;
    }
    public void setyCoor(float yCoor) {
        this.yCoor = yCoor;
    }
    public float getzCoor() {
        return zCoor;
    }
    public void setzCoor(float zCoor) {
        this.zCoor = zCoor;
    }
}
