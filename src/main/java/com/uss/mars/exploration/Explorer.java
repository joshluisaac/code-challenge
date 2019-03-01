package com.uss.mars.exploration;

public class Explorer extends AbstractTableTopOccupant {

    OccupantType type;
    Coordinate coordinate;

    public Explorer(){
        type = OccupantType.EXPLORER;
    }


    @Override
    boolean coordinateIsValid(Coordinate coordinate) {
        return MovementUtils.pathIsValid(coordinate);
    }

    @Override
    OccupantType getOccupantType() {
        return null;
    }
}
