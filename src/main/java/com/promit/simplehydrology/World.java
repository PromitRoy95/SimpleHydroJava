package com.promit.simplehydrology;

import processing.core.PApplet;
import processing.core.PVector;

public class World {

    static int SEED;

    static float scale = 60.0f;

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
    public static void generate(PApplet applet, int side) {
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

    static PVector surfaceNormal( int i, int j) {
        PVector n = new PVector(
            scale * (heightMap[i][j]-heightMap[i+1][j]),
            1.0f, 
            0.0f
        ).normalize().mult(0.15f);

        n.add(new PVector(
            scale * (heightMap[i-1][j]-heightMap[i][j]),
            1.0f, 
            0.0f
        ).normalize().mult(0.15f));

        n.add(new PVector(
            0.0f,
            1.0f,
            scale * (heightMap[i][j]-heightMap[i][j+1])
        ).normalize().mult(0.15f));

         n.add(new PVector(
            0.0f,
            1.0f,
            scale * (heightMap[i][j-1]-heightMap[i][j])
        ).normalize().mult(0.15f));

        //Diagonals! (This removes the last spatial artifacts)
        n.add(new PVector(
            scale*(heightMap[i][j] - heightMap[i+1][j+1])/1.414f,
            1.414f,
            scale*(heightMap[i][j]-heightMap[i+1][j+1])/1.414f
        ).normalize().mult(0.1f));

        n.add(new PVector(
            scale*(heightMap[i][j] - heightMap[i+1][j-1])/1.414f,
            1.414f,
            scale*(heightMap[i][j]-heightMap[i+1][j-1])/1.414f
        ).normalize().mult(0.1f));

        n.add(new PVector(
            scale*(heightMap[i][j] - heightMap[i-1][j+1])/1.414f,
            1.414f,
            scale*(heightMap[i][j]-heightMap[i-1][j+1])/1.414f
        ).normalize().mult(0.1f));

        n.add(new PVector(
            scale*(heightMap[i][j] - heightMap[i-1][j-1])/1.414f,
            1.414f,
            scale*(heightMap[i][j]-heightMap[i-1][j-1])/1.414f
        ).normalize().mult(0.1f));
        return n;
    }

    static void erode(int cycles) {
        for(int i=0; i<cycles; i++) {
            
        }
    }
}
