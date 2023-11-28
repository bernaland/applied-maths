package com.uelbosque.appliedMaths.figures;

import static org.lwjgl.opengl.GL11.*;

public class Selector extends ShapeBase {

    @Override
    protected void buildShape() {
        glTranslatef(this.translation.getxCoor(), this.translation.getyCoor() + 1.5f, this.translation.getzCoor() - 6);
        
        glRotatef(-90, 1, 0, 0);

        double step = Math.PI / 30;
        glBegin(GL_TRIANGLE_STRIP);
        for (double theta = 0; theta <= 2 * Math.PI; theta += step) {
            CircleVertex v1 = new CircleVertex(Math.PI / 3, theta, 0.7),
                v2 = new CircleVertex(Math.PI / 3, theta + step, 0.7);

            glColor3d(1, 0, 0);
            glVertex3d(0, 0, 0);
            glColor3d(0, 1, 1);
            glVertex3d(v1.getX(), v1.getY(), v1.getZ());
            glVertex3d(v2.getX(), v2.getY(), v2.getZ());
        }
        glEnd();
    }
    
}
