package com.promit.simplehydrology;

import processing.core.PVector;

public class Particle {
    public PVector pos;
    public PVector speed = new PVector(0f, 0f);
    
    // Total particle volume
    public float volume;
    // Fraction of the volume that is sediment
    public float sediment;
    // Water deposition rate
    // How flooding translates volume into water level
    public static final double volumeFactor = 100.0f;

    public Particle(PVector pos) {
        this.pos = pos;
    }

    void descent(float[] h, float[] stream, float[] pool, boolean track, PVector dim, float scale) {

    }

    void flood(float[] h, float[] pool, PVector dim) {

    }
}
