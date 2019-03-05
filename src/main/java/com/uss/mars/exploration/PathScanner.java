package com.uss.mars.exploration;

public class PathScanner {



int findPath(String[][] matrix, Coordinate src, Coordinate dest){
    boolean x = matrix[src.getCoordinateX()][src.getCoordinateY()].equals("X") &&
            matrix[dest.getCoordinateX()][dest.getCoordinateY()].equals("X");
    if(!x) return -1;



    return 0;

}

}
