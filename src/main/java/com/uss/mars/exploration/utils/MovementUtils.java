package com.uss.mars.exploration.utils;

import com.uss.mars.exploration.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility providing file explorer movement methods.
 */
public class MovementUtils {

    private MovementUtils() {
    }

    private static final int X_MAX = 5;
    private static final int Y_MAX = 5;

    // checks if the coordinates specified satisfies the boundaries of the grid

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
        return coordinate.getCoordinateX() < X_MAX && coordinate.getCoordinateY() < Y_MAX
                && coordinate.getCoordinateX() >= 0 && coordinate.getCoordinateY() >= 0;
    }

}
