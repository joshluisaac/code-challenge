package com.uss.mars.exploration;

public class Explorer extends AbstractTableTopOccupant {

    private OccupantType type;
    private Coordinate coordinate;

    public Explorer(Coordinate coordinate) {
        type = OccupantType.EXPLORER;
        this.coordinate = coordinate;
    }

    /**
     * Checks for coordinate validity.
     * Coordinate is said to be valid if it's X and Y position falls within the stipulated boundaries
     * <p>
     * The value of this is then used to prevent explorer from destruction.
     *
     * @param coordinate the X,Y coordinates of the explorer.
     * @return returns true or false.
     *
     */

    @Override
    public boolean coordinateIsValid() {
        return MovementUtils.pathIsValid(this.coordinate);
    }

    public boolean coordinateIsValid(final Coordinate coordinate) {
        return coordinateIsValid();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public OccupantType getOccupantType() {
        return type;
    }


    void occupyUnit() {

        //check coordinate validity

        // if it is an origin path reset blocks otherwise move to position

        //move to position
        //if true reset blocks


    }


}
