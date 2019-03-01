package com.uss.mars.exploration;

public abstract class AbstractTableTopOccupant {

    abstract boolean coordinateIsValid(final Coordinate coordinate);
    abstract OccupantType getOccupantType();
}
