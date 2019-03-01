package com.uss.mars.exploration;

public class Explorer extends AbstractTableTopOccupant {

    private OccupantType type;
    private Coordinate coordinate;

    public Explorer(Coordinate coordinate){
        type = OccupantType.EXPLORER;
        this.coordinate = coordinate;
    }


    @Override
    boolean coordinateIsValid(final Coordinate coordinate) {
        return MovementUtils.pathIsValid(coordinate);
    }

    @Override
    OccupantType getOccupantType() {
        return null;
    }
}
