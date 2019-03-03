package com.uss.mars.exploration;

public class Blocker implements TableTopOccupant {

    private OccupantType type;
    private Coordinate coordinate;

    public Blocker(Coordinate coordinate) {
        type = OccupantType.BLOCKER;
        this.coordinate = coordinate;
    }

    @Override
    public OccupantType getOccupantType() {
        return type;
    }


    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "Blocker{" +
                "type=" + type +
                ", coordinate=" + coordinate +
                '}';
    }
}
