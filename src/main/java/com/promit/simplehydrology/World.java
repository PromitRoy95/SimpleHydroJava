package com.promit.simplehydrology;

import processing.core.PShape;
import processing.core.PVector;

public class World {
    static PShape mesh;
    static PVector[][] vertices;

    static int SEED;

    //-- simulation state
    float[][] heightMap;
    float[][] streamMap;
    float[][] poolMap;
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
    // vertices means number of vertices on one side
    public static void init(int side, int distance) {
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
        vertices = new PVector[side][side];
    }

    // Update methods

    // erosion update step
    static void erode(int cycles) {

    }

    // sediment cascade
    static void cascade(PVector pos) {

    }

    public static PShape getMesh() {
        return mesh;
    }
}
