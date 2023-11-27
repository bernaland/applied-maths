package com.uelbosque.appliedMaths.figures;

class CircleVertex {
    private double x;
    private double y;
    private double z;
    private double nx;
    private double ny;
    private double nz;

    public CircleVertex(double phi, double theta, double radius) {
        nx = Math.cos(phi) * Math.sin(theta);
        ny = Math.cos(phi) * Math.cos(theta);
        nz = Math.sin(phi);
        x = radius * nx;
        y = radius * ny;
        z = radius * nz;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Vertex [x=" + x + ", y=" + y + ", z=" + z + "]";
    }

    public double getNx() {
        return nx;
    }

    public double getNy() {
        return ny;
    }

    public double getNz() {
        return nz;
    }
}
