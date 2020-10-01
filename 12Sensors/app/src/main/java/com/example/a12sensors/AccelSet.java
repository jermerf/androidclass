package com.example.a12sensors;

public class AccelSet {
    private float x;
    private float y;
    private float z;

    public AccelSet(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
