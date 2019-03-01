package com.uss.mars.exploration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExplorerMovement {

    private static final List<Integer> POSSIBLE_VALUES = new ArrayList<>(Arrays.asList(0,1,2,3,4));

    //checks if the coordinates specified satisfies the boundaries of the grid

    public boolean movementIsValid(final int xCoordinate, final int yCoordinate) {
        final boolean xCoordIsValid =  POSSIBLE_VALUES.contains(xCoordinate);
        final boolean yCoordIsValid =  POSSIBLE_VALUES.contains(yCoordinate);
        return xCoordIsValid && yCoordIsValid;
    }

}
