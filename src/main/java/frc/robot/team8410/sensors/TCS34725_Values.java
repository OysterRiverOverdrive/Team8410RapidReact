// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.team8410.sensors;

/** Add your docs here. */
public class TCS34725_Values

{
    private int r, b, g, c;

    public TCS34725_Values(int r, int b, int g, int c) {
        this.r = r;
        this.b = b;
        this.g = g;
        this.c = c;
    }

    public int getR() {
        return this.r;
    }

    public int getB() {
        return this.b;
    }

    public int getG() {
        return this.g;
    }

    public int getC() {
        return this.c;
    }

    public String toString() {
        return "[ r:" + Integer.toString(r) +
                ", b:" + Integer.toString(b) +
                ", g:" + Integer.toString(g) +
                ", c:" + Integer.toString(c) + "]";
    }
}
