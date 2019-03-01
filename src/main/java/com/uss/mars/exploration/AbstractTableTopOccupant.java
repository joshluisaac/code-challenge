package com.uss.mars.exploration;

public abstract class AbstractTableTopOccupant implements TableTopOccupant {

    public abstract boolean coordinateIsValid(final Coordinate coordinate);
    public abstract boolean coordinateIsValid();
    public abstract OccupantType getOccupantType();
}
