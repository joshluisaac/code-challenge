package com.uss.mars.exploration;

public interface TableTopOccupant {

    boolean coordinateIsValid(final Coordinate coordinate);
    boolean coordinateIsValid();
    OccupantType getOccupantType();

}
