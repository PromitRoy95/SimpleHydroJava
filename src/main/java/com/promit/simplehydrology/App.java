package com.promit.simplehydrology;

import processing.core.PApplet;;
/**
 * Hello world!
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

    @Override
    public void setup() {
        background(0);
    }

    @Override
    public void draw() {
        background(30);
        lights();
        translate(width / 2f, height / 2f, 0);
        rotateX(frameCount * 0.01f);
        rotateY(frameCount * 0.02f);
        box(150);
    }
}
