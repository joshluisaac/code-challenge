package com.uss.mars.exploration;

public class Coordinate {

    public int xCoordinate;
    public int yCoordinate;

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

    @Override
    public String toString() {
        return "Coordinate{" +
                "xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }
}
