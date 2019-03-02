package com.uss.mars.exploration;

public class MarsExplorer implements TableTopOccupant {

    private OccupantType type;
    private Coordinate coordinate;

    public MarsExplorer(Coordinate coordinate) {
        type = OccupantType.MARS_EXPLORER;
        this.coordinate = coordinate;
    }

    /**
     * Checks for coordinate validity. Coordinate is said to be valid if it's X and
     * Y position falls within the stipulated boundaries
     * <p>
     * The value of this is then used to prevent explorer from destruction.
     *
     * @param coordinate the X,Y coordinates of the explorer.
     * @return returns true or false.
     *
     */

    @Override
    public boolean coordinateIsValid(final Coordinate coordinate) {
        return coordinateIsValid();
    }

    @Override
    public boolean coordinateIsValid() {
        return MovementUtils.pathIsValid(this.coordinate);
    }

    @Override
    public OccupantType getOccupantType() {
        return type;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
