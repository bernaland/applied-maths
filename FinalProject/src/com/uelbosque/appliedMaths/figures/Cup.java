package com.uelbosque.appliedMaths.figures;

import static org.lwjgl.opengl.GL11.*;

public class Cup extends ShapeBase {
    private double stepSize = Math.PI / 30;
    private float height;
    private float topWidth;
    private float bottomWidth;
    // private float rotation = 0;

    public Cup(float topWidth, float bottomWidth, float height) {
        super();
        float min = Math.min(height, topWidth);
        min = Math.min(min, bottomWidth);

        this.height = height / min;
        this.topWidth = topWidth / min;
        this.bottomWidth = bottomWidth / min;
    }

    @Override
    protected void buildShape() {
        glTranslatef(this.translation.getxCoor(), this.translation.getyCoor(), this.translation.getzCoor() - 6);
        
        glRotatef(this.rotation.getAngle(), this.rotation.getX(), this.rotation.getY(), this.rotation.getZ());
        int sectors = 10;
        double widthDelta = (bottomWidth - topWidth) / sectors;
        double heightDelta = height / sectors;
        
        for (double theta = 0; theta <= 2 * Math.PI; theta += stepSize) {
            CircleVertex top1 = new CircleVertex(0, theta, topWidth),
                top2 = new CircleVertex(0, theta + stepSize, topWidth);

            if (theta < Math.PI) {
                glColor3f(1, 0, 0);
            } else {
                glColor3f(0, 1, 0);
            }
            glBegin(GL_LINES);
            glVertex3d(top1.getX(), top1.getY(), top1.getZ());
            glNormal3d(top1.getNx(), top1.getNy(), top1.getNz());
            glVertex3d(top2.getX(), top2.getY(), top2.getZ());
            glNormal3d(top2.getNx(), top2.getNy(), top2.getNz());
            glEnd();
            
            if (theta < Math.PI) {
                glColor3f(0, 1, 0);
            } else {
                glColor3f(1, 0, 0);
            }
            glBegin(GL_TRIANGLES);
            glVertex3d(0, 0, 0);
            glNormal3d(0, 0, 0);
            glVertex3d(top1.getX(), top1.getY(), top1.getZ());
            glNormal3d(top1.getNx(), top1.getNy(), top1.getNz());
            glVertex3d(top2.getX(), top2.getY(), top2.getZ());
            glNormal3d(top2.getNx(), top2.getNy(), top2.getNz());

            for (double w = topWidth, h = 0; w <= bottomWidth; w += widthDelta, h += heightDelta) {
                double topRadius = Math.sqrt(Math.pow(w, 2) +  Math.pow(h, 2)),
                    bottomRadius = Math.sqrt(Math.pow(w + widthDelta, 2) +  Math.pow(h + heightDelta, 2));
                double topAngle = Math.asin(h / topRadius),
                    bottomAngle = Math.asin((h + heightDelta) / bottomRadius);
                    
                CircleVertex v1 = new CircleVertex(topAngle, theta, topRadius),
                    v2 = new CircleVertex(bottomAngle, theta, bottomRadius),
                    v3 = new CircleVertex(bottomAngle, theta + stepSize, bottomRadius),
                    v5 = new CircleVertex(topAngle, theta + stepSize, topRadius);
                
                glVertex3d(v1.getX(), v1.getY(), v1.getZ());
                glNormal3d(v1.getNx(), v1.getNy(), v1.getNz());
                glVertex3d(v2.getX(), v2.getY(), v2.getZ());
                glNormal3d(v2.getNx(), v2.getNy(), v2.getNz());
                glVertex3d(v3.getX(), v3.getY(), v3.getZ());
                glNormal3d(v3.getNx(), v3.getNy(), v3.getNz());
                
                glVertex3d(v1.getX(), v1.getY(), v1.getZ());
                glNormal3d(v1.getNx(), v1.getNy(), v1.getNz());
                glVertex3d(v5.getX(), v5.getY(), v5.getZ());
                glNormal3d(v5.getNx(), v5.getNy(), v5.getNz());
                glVertex3d(v3.getX(), v3.getY(), v3.getZ());
                glNormal3d(v3.getNx(), v3.getNy(), v3.getNz());
                
            }
            glEnd();
            
            double bottomRadius = Math.sqrt(Math.pow(height, 2) + Math.pow(bottomWidth, 2)),
                bottomAngle = Math.asin(height / bottomRadius);
            CircleVertex v2 = new CircleVertex(bottomAngle, theta, bottomRadius),
                v3 = new CircleVertex(bottomAngle, theta + stepSize, bottomRadius);

            if (theta < Math.PI) {
                glColor3f(1, 0, 0);
            } else {
                glColor3f(0, 1, 0);
            }
            glBegin(GL_LINES);
            glVertex3d(v2.getX(), v2.getY(), v2.getZ());
            glNormal3d(v2.getNx(), v2.getNy(), v2.getNz());
            glVertex3d(v3.getX(), v3.getY(), v3.getZ());
            glNormal3d(v3.getNx(), v3.getNy(), v3.getNz());
            glEnd();
        }
        
        // rotation += 0.5;
    }
    
}
