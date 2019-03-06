package com.uss.mars.exploration;

import com.uss.mars.exploration.utils.MovementUtils;
import com.uss.mars.exploration.utils.PathFindingUtils;

public class PathScanner {

    ITableTop tableTop;
    private static final int MOVE_COST = 10;

    public PathScanner(ITableTop tableTop){
        this.tableTop = tableTop;
    }





int findPath2(String[][] matrix, Coordinate src, Coordinate dest){
    boolean x = matrix[src.getCoordinateX()][src.getCoordinateY()].equals("X") &&
            matrix[dest.getCoordinateX()][dest.getCoordinateY()].equals("X");
    if(!x) return -1;
    return 0;

}


public PathUnit[][] heuristicValue(Coordinate dest){
    String[][] matrix = tableTop.getMatrix();
    PathUnit[][] matrixPath = new PathUnit[5][5];
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            Coordinate src = new Coordinate(i,j);
            int hVal = PathFindingUtils.manhattanDistance(src,dest);
            PathUnit pathUnit = new PathUnit();
            pathUnit.sethVal(hVal);
            matrixPath[i][j] = pathUnit;
            pathUnit.setCoordinate(src);
            //System.out.println(pathUnit.toString());
        }
    }
    return matrixPath;
}

public void print(PathUnit[][] matrixPath){
    for (int i = 0; i < matrixPath.length; i++) {
        for (int j = 0; j < matrixPath[i].length; j++) {
            System.out.println(matrixPath[i][j].toString());
        }
    }
}


    public PathUnit[][] findPath(PathUnit[][] matrixPath, Coordinate src,Coordinate dest){
        int srcPointX = src.xCoordinate;
        int srcPointY = src.yCoordinate;

        int fTmp = -1;

        //go to down
        Coordinate downSlot = new Coordinate(srcPointX + 1,srcPointY);
        boolean downMvt = MovementUtils.coordinatePathIsValid(downSlot);
        if(downMvt && tableTop.slotIsAvailable(downSlot)) {
            int fValDown = matrixPath[srcPointX + 1][srcPointY].gethVal() + MOVE_COST;
            int gValDown = matrixPath[srcPointX + 1][srcPointY].getgVal() + 10;
            matrixPath[srcPointX + 1][srcPointY].setgVal(gValDown);
            matrixPath[srcPointX + 1][srcPointY].setfVal(fValDown);
        }

        //go to top
        Coordinate upSlot = new Coordinate(srcPointX - 1,srcPointY);
        boolean upMvt = MovementUtils.coordinatePathIsValid(upSlot);
        if(upMvt && tableTop.slotIsAvailable(upSlot)) {
            int fValTop = matrixPath[srcPointX - 1][srcPointY].gethVal() + MOVE_COST;
            int gValTop = matrixPath[srcPointX - 1][srcPointY].getgVal() + 10;
            matrixPath[srcPointX - 1][srcPointY].setgVal(gValTop);
            matrixPath[srcPointX - 1][srcPointY].setfVal(fValTop);
        }

        //go to right
        Coordinate rightSlot = new Coordinate(srcPointX,srcPointY + 1);
        boolean rightMvt = MovementUtils.coordinatePathIsValid(rightSlot);
        if(rightMvt && tableTop.slotIsAvailable(rightSlot)) {
            int fVal = matrixPath[srcPointX][srcPointY + 1].gethVal() + MOVE_COST;
            int gVal = matrixPath[srcPointX][srcPointY + 1].getgVal() + 10;
            matrixPath[srcPointX][srcPointY + 1].setgVal(gVal);
            matrixPath[srcPointX][srcPointY + 1].setfVal(fVal);
        }

        //go to left
        Coordinate leftSlot = new Coordinate(srcPointX,srcPointY - 1);
        boolean leftMvt = MovementUtils.coordinatePathIsValid(leftSlot);
        if(leftMvt && tableTop.slotIsAvailable(leftSlot)) {
            int fVal = matrixPath[srcPointX][srcPointY - 1].gethVal() + MOVE_COST;
            int gVal = matrixPath[srcPointX][srcPointY - 1].getgVal() + 10;
            matrixPath[srcPointX][srcPointY - 1].setgVal(gVal);
            matrixPath[srcPointX][srcPointY - 1].setfVal(fVal);
        }

        //decision




return matrixPath;
    }

    void process(){

    }






    public static void main(String[] args) {
        PathScanner pathScanner = new PathScanner(new TableTop());
        PathUnit[][] matrixPath = pathScanner.heuristicValue(new Coordinate(2,2));
        pathScanner.findPath(matrixPath, new Coordinate(0,0), new Coordinate(2,2));
        pathScanner.print(matrixPath);
    }

}
