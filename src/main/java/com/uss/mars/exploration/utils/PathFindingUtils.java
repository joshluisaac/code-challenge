package com.uss.mars.exploration.utils;

import com.uss.mars.exploration.Coordinate;

public class PathFindingUtils {

    public static int manhattanDistance(Coordinate p1, Coordinate p2){
        return Math.abs(p1.xCoordinate - p2.xCoordinate) + Math.abs(p1.yCoordinate - p2.yCoordinate);
    }

}
