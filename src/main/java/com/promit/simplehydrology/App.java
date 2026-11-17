package com.promit.simplehydrology;

import processing.core.PApplet;
import processing.core.PShape;;
/**
 * PApplet is the sketch
 */
public class App extends PApplet {
    public static void main(String[] args) {
        // Pass the exact package and class name to launch the sketch
        PApplet.main("com.promit.simplehydrology.App");
    }

    @Override
    public void settings() {
        // Canvas size must be set inside settings() when using standard Java
        size(1080, 720, P3D);
    }

    PShape mesh;

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
    }

    @Override
    public void draw() {

        background(50);
        lights();


        translate(width/2, height/2, 0);
        rotateX(PI / 4);
        shape(mesh);
    }
}
