package com.uss.mars.exploration;

public class Blocker implements TableTopOccupant {

    private OccupantType type;
    private Coordinate coordinate;

    public Blocker(Coordinate coordinate) {
        type = OccupantType.BLOCKER;
        this.coordinate = coordinate;
    }


    @Override
    public boolean coordinateIsValid(Coordinate coordinate) {
        return false;
    }

    @Override
    public boolean coordinateIsValid() {
        return false;
    }

    @Override
    public OccupantType getOccupantType() {
        return null;
    }
}
