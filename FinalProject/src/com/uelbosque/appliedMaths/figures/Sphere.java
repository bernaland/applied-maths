package com.uelbosque.appliedMaths.figures;

import static org.lwjgl.opengl.GL11.*;

public class Sphere extends ShapeBase {
    private final double step = Math.PI / 12;
    private final float currentRadius;

    public Sphere(float radius) {
        super();
        this.currentRadius = radius;
    }

    @Override
    protected void buildShape() {
        int red = 1, green = 0;
        glTranslatef(this.translation.getxCoor(), this.translation.getyCoor(), this.translation.getzCoor() - 4);
        glRotated(60, 1, 0, 0);
        glRotated(60, 0, 1, 0);
        for (double phi = -Math.PI / 2; phi <= Math.PI / 2; phi += step) {
            glBegin(GL_TRIANGLES);
            for (double theta = 0; theta <= 2 * Math.PI; theta += step) {
                glColor3f(red, green, 10);
                CircleVertex v1 = new CircleVertex(phi, theta, currentRadius),
                    v2 = new CircleVertex(phi + step, theta, currentRadius),
                    v3 = new CircleVertex(phi + step, theta + step, currentRadius);
                glNormal3d(v1.getNx(), v1.getNy(), v1.getNz());
                glVertex3d(v1.getX(), v1.getY(), v1.getZ());
                glNormal3d(v2.getNx(), v2.getNy(), v2.getNz());
                glVertex3d(v2.getX(), v2.getY(), v2.getZ());
                glNormal3d(v3.getNx(), v3.getNy(), v3.getNz());
                glVertex3d(v3.getX(), v3.getY(), v3.getZ());

                CircleVertex v4 = new CircleVertex(phi, theta, currentRadius),
                    v5 = new CircleVertex(phi + step, theta + step, currentRadius),
                    v6 = new CircleVertex(phi, theta + step, currentRadius);
                glNormal3d(v4.getNx(), v4.getNy(), v4.getNz());
                glVertex3d(v4.getX(), v4.getY(), v4.getZ());
                glNormal3d(v5.getNx(), v5.getNy(), v5.getNz());
                glVertex3d(v5.getX(), v5.getY(), v5.getZ());
                glNormal3d(v6.getNx(), v6.getNy(), v6.getNz());
                glVertex3d(v6.getX(), v6.getY(), v6.getZ());
            }
            glEnd();
            red = Math.abs(red - 1);
            green = Math.abs(green - 1);
        }
    }
    
}
