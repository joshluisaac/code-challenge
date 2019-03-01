package com.uss.mars.exploration;

public abstract class AbstractTableTopOccupant {



    abstract boolean coordinateIsValid(final int xCoordinate, final int yCoordinate);
    abstract OccupantType getOccupantType();
}
