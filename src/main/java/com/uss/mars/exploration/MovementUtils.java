package com.uss.mars.exploration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovementUtils {

    private static final List<Integer> POSSIBLE_VALUES = new ArrayList<>(Arrays.asList(0,1,2,3,4));

    //checks if the coordinates specified satisfies the boundaries of the grid

    public static boolean pathIsValid(Coordinate coordinate) {
        final boolean xCoordIsValid =  POSSIBLE_VALUES.contains(coordinate.getCoordinateX());
        final boolean yCoordIsValid =  POSSIBLE_VALUES.contains(coordinate.getCoordinateY());
        return xCoordIsValid && yCoordIsValid;
    }

}
