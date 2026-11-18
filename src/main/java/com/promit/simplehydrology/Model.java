package com.promit.simplehydrology;

import processing.core.PVector;

// Helper class to store vertices
public class Model {
    public PVector[][] vertices;

    public Model(int sideCount, int sideLength){
        vertices = new PVector[sideCount][sideCount];
        // do it correctly
        for(int i=0; i<sideCount; i++) {
            for(int j=0; j<sideCount; j++) {
                vertices[i][j] = new PVector(i*sideLength, j*sideLength, 0.0f);
            }
        }
        
    }

    public void updateModel(float[][] heightMap) {
        for(int i=0; i<heightMap.length; i++) {
            for(int j=0; j<heightMap[i].length; j++) {
                vertices[i][j].z = heightMap[i][j];
            }
        }
    }

}
