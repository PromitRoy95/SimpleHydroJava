package com.promit.simplehydrology;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PVector;

public class World {

    static int SEED;
    static Random random = new Random();
    static float scale = 30.0f;

    //-- simulation state
    static float[][] heightMap;
    static VertexColor[][] colors;

    //--

    //Erosion Steps
    boolean active = false;
    int remaining = 200000;
    int erosionstep = 1000;

    // Parameters
    static float dt = 1.2f;
    static float density = 3.0f;
    static float evapRate = 0.001f;
    static float depositionRate = 0.1f;
    static float minVol = 0.01f;
    static float friction = 0.05f;



    static World w = new World();

    private World() {
        
    }

    /* In P3D, x axis increases towards the right,
        y increases downwards, z increases towards the user
        
     */
    public static void generate(PApplet applet, int side) {
        float min=0, max = 0;
        heightMap = new float[side][side];
        colors = new VertexColor[side][side];

        for(int i=0; i<side; i++) {
            for(int j = 0; j<side; j++) {
                heightMap[i][j] = applet.noise(i*0.01f, j*0.01f)*0.6f;
                heightMap[i][j] += applet.noise(i*0.05f, j*0.05f)*0.4f;
                colors[i][j] = new VertexColor(112, 91, 33);
                //colors[i][j] = new VertexColor(94, 173, 49);
            }
        }
        for(int i=0; i<side; i++) {
            for(int j = 0; j<side; j++) {
                if(heightMap[i][j]>max) 
                    max = heightMap[i][j];
                if(heightMap[i][j]<min) 
                    min = heightMap[i][j];
            }
        }
        //normalize
        /*
        for(int i=0; i<side; i++) {
            for(int j = 0; j<side; j++) {
                heightMap[i][j] = (heightMap[i][j] - min)/(max-min);
            }
        }*/
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
            int posX = random.nextInt(128-2) + 1;
            int posY = random.nextInt(128-2) + 1;
            //spawn at random position
            Particle drop = new Particle(new PVector(posX, posY));
            while(drop.volume > minVol) {
                int[] ipos = new int[2];
                ipos[0] = (int)drop.pos.x;
                ipos[1] = (int)drop.pos.y;

                PVector n = surfaceNormal(ipos[0], ipos[1]);

                // Accelerate particle using newtonian mechanics
                drop.speed.add(new PVector(n.x, n.z).div(drop.volume*density).mult(dt));
                drop.pos.add(PVector.mult(drop.speed, dt));
                drop.speed = drop.speed.mult(1.0f - dt*friction);

                if(drop.pos.x < 1 || drop.pos.y < 1 || drop.pos.x>=heightMap.length-1 || drop.pos.y>=heightMap[0].length-1) {
                    break;
                }

                float maxsediment = drop.volume * drop.speed.mag()*
                (heightMap[ipos[0]][ipos[1]]-heightMap[(int)drop.pos.x][(int)drop.pos.y]);
            
                if(maxsediment < 0.0) maxsediment = 0.0f;

                float sdiff = maxsediment - drop.sediment;
                //System.out.println(sdiff);
                // Act on the heightmap and droplet
                drop.sediment += dt*depositionRate*sdiff;
                heightMap[ipos[0]][ipos[1]] -= dt*drop.volume*depositionRate*sdiff;
                
                colors[ipos[0]][ipos[1]] = new VertexColor(94, 173, 49);

                //evaporate the droplet
                drop.volume *= (1.0-dt*evapRate);

            }
        }
    }
}
