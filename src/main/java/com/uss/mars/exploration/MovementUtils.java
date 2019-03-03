package com.uss.mars.exploration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovementUtils {

    private MovementUtils(){}

    private static final List<Integer> POSSIBLE_VALUES = new ArrayList<>(Arrays.asList(0,1,2,3,4));

    //checks if the coordinates specified satisfies the boundaries of the grid

    /**
     * Checks for coordinate validity. Coordinate is said to be valid if it's X and
     * Y position falls within the stipulated boundaries.
     * <p>
     * The value of this is then used to prevent explorer from destruction.
     *
     * @param coordinate the X,Y coordinates of the explorer.
     * @return returns true or false.
     *
     */
    public static boolean coordinatePathIsValid(Coordinate coordinate) {
        final boolean xCoordIsValid =  POSSIBLE_VALUES.contains(coordinate.getCoordinateX());
        final boolean yCoordIsValid =  POSSIBLE_VALUES.contains(coordinate.getCoordinateY());
        return xCoordIsValid && yCoordIsValid;
    }

}
