package com.uss.mars.exploration;

public class Coordinate {

    private int xCoordinate;
    private int yCoordinate;

    public Coordinate(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getCoordinateX() {
        return xCoordinate;
    }

    public int getCoordinateY() {
        return yCoordinate;
    }
}
