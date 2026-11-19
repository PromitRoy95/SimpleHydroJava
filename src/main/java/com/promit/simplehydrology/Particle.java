package com.promit.simplehydrology;

import processing.core.PVector;

public class Particle {
    public PVector pos;
    public PVector speed = new PVector(0f, 0f);
    
    // Total particle volume
    public float volume = 1.0f;
    // Fraction of the volume that is sediment
    public float sediment = 0.0f;

    public Particle(PVector pos) {
        this.pos = pos;
    }
}
