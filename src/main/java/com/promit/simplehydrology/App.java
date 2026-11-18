package com.promit.simplehydrology;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
/**
 * PApplet is the sketch
 */
public class App extends PApplet {
    PShape mesh;
    Model vertices;

    // update the mesh using the model
    public void updateMesh(Model m) {
        // update the vertices here
        mesh.beginShape(PConstants.TRIANGLES);
        mesh.fill(58, 219, 0);
        mesh.stroke(255);

        for(int i=0; i<m.vertices.length-1; i++) {
            for(int j=0;j<m.vertices[i].length-1;j++) {
                //the quad generation code here
                // our two triangles are
                // (i,j) , (i+1, j), (i, j+1)
                // (i, j+1), (i+1, j), (i+1, j+1)
                mesh.vertex(m.vertices[i][j].x, m.vertices[i][j].y, m.vertices[i][j].z);
                mesh.vertex(m.vertices[i+1][j].x, m.vertices[i+1][j].y, m.vertices[i+1][j].z);
                mesh.vertex(m.vertices[i][j+1].x, m.vertices[i][j+1].y, m.vertices[i][j+1].z);

                mesh.vertex(m.vertices[i][j+1].x, m.vertices[i][j+1].y, m.vertices[i][j+1].z);
                mesh.vertex(m.vertices[i+1][j].x, m.vertices[i+1][j].y, m.vertices[i+1][j].z);
                mesh.vertex(m.vertices[i+1][j+1].x, m.vertices[i+1][j+1].y, m.vertices[i+1][j+1].z);
            }
        }
        mesh.endShape(PConstants.CLOSE);
    }

    public static void main(String[] args) {
        // Pass the exact package and class name to launch the sketch
        PApplet.main("com.promit.simplehydrology.App");
    }

    @Override
    public void settings() {
        // Canvas size must be set inside settings() when using standard Java
        size(1080, 720, P3D);
    }

    /*
    In Processing, beginShape() without arguments 
    creates a single continuous polygon 
    by connecting all vertices in order, 
    whereas beginShape(TRIANGLES) groups 
    every three consecutive vertices into 
    separate, independent triangles.
     */
    @Override
    public void setup() {
        background(50);

        mesh = createShape();
        vertices = new Model(64, 10);
        updateMesh(vertices);
        
        /* 
        mesh.beginShape(TRIANGLES);
        mesh.fill(58, 219, 0);
        mesh.stroke(255);
        mesh.vertex(-100, -100, 0);
        mesh.vertex( 100, -100, 0);
        mesh.vertex(-100, 100);

        mesh.vertex( -100,  100, 0);
        mesh.vertex(100,  -100, 0);
        mesh.vertex(100,  100, 0);

        mesh.endShape(CLOSE);
        */
    }

    @Override
    public void draw() {

        background(50);
        lights();
        ortho();


        //translate(width/2, height/2, 0);
        translate(100, height/2, 0);

        rotateX(PI / 4);
        rotateZ(-PI/4);
        shape(mesh);
    }
}
