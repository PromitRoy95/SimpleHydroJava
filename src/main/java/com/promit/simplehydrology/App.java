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

    /** Vertices
     *  0----1----2
        |  / |  / |
        | /  | /  |
        3----4----5
        |  / |  / |
        | /  | /  |
        6----7----8

        0 1 3
        3 1 4
        1 2 4
        4 2 5
        3 4 6
        6 4 7
        4 5 7
        7 5 8
        */
    // update the mesh using the model
    public void updateMesh(Model m) {
        // update the vertices here
        mesh.beginShape(PConstants.TRIANGLES);
        mesh.fill(58, 219, 0);
        //mesh.stroke(255);
        mesh.noStroke();

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
        background(139, 199, 252);

        mesh = createShape();
        World.generate(this, 128);
        vertices = new Model(128, 5);
        vertices.updateModel(World.heightMap);
        mesh = createShape();
        updateMesh(vertices);
    
    }

    @Override
    public void draw() {

        background(139, 199, 252);
        lights();
        ortho();


        //translate(width/2, height/2, 0);
        translate(100, height/2 + 100, 0);

        rotateX(PI / 4);
        rotateZ(-PI/4);

        World.erode(1000);
        vertices.updateModel(World.heightMap);
        updateMesh(vertices);
        shape(mesh);
    }
}
