package com.tw.step.rover.boundary;

import com.tw.step.rover.position.Coordinate;

public class Plateau implements Boundary {
    private final Coordinate bottomLeft;
    private final Coordinate topRight;

    public Plateau(Coordinate bottomLeft, Coordinate topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }
    public Plateau(Coordinate topRight) {
        this.bottomLeft = new Coordinate(0,0);
        this.topRight = topRight;
    }

    @Override
    public boolean isWithin(Coordinate coord) {
        return coord.isWithin(bottomLeft,topRight);
    }
}
