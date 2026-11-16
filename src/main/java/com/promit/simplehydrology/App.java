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
        size(600, 400, P3D);
    }

    PShape mesh;

    @Override
    public void setup() {
        background(30);
        mesh = createShape();
        mesh.beginShape(TRIANGLES);
        mesh.fill(100, 150, 255);
        mesh.noStroke();
        mesh.vertex(0, 0, 0);
        mesh.vertex(100, 0, 0);
        mesh.vertex(0, 100, 0);
        mesh.endShape();
    }

    @Override
    public void draw() {
        background(30);
        lights();
        translate(width / 2f, height / 2f, 0);
        rotateY(frameCount * 0.01f);
        shape(mesh);
    }
}
