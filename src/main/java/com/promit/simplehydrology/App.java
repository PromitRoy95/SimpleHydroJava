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
        size(600, 400);
    }

    @Override
    public void setup() {
        background(30);
    }

    @Override
    public void draw() {
        fill(0, 200, 255);
        noStroke();
        ellipse(mouseX, mouseY, 40, 40);
    }
}
