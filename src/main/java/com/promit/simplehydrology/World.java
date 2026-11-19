package com.promit.simplehydrology;

import processing.core.PApplet;
import processing.core.PVector;

public class World {

    static int SEED;

    //-- simulation state
    static float[][] heightMap;
    static float[][] streamMap;
    static float[][] poolMap;
    //--

    // Parameters
    static float lrate = 0.1f;
    static float dischargeThresh = 0.0f;
    static float maxdiff = 0.01f;
    static float settling = 0.8f;

    static World w = new World();

    private World() {
        
    }

    /* In P3D, x axis increases towards the right,
        y increases downwards, z increases towards the user
        
     */
    public static void init(PApplet applet, int side) {
        heightMap = new float[side][side];
        for(int i=0; i<side; i++) {
            for(int j = 0; j<side; j++) {
                heightMap[i][j] = applet.noise(i*0.1f, j*0.1f) * 100;
            }
        }
        for(int i=0; i<side; i++) {
            for(int j = 0; j<side; j++) {
                heightMap[i][j] += applet.noise(i, j) * 10;
            }
        }
    }

    // Update methods

    // erosion update step
    static void erode(int cycles) {

    }

    // sediment cascade
    static void cascade(PVector pos) {

    }

}
