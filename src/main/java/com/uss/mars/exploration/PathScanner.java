package com.uss.mars.exploration;

import com.uss.mars.exploration.utils.MovementUtils;
import com.uss.mars.exploration.utils.PathFindingUtils;

import java.util.ArrayList;
import java.util.List;

public class PathScanner {

    private ITableTop tableTop;
    private static final int MOVE_COST = 10;
    private List<PathUnit> closedList = new ArrayList<>();
    private StringBuilder builder = new StringBuilder();

    public PathScanner(ITableTop tableTop) {
        this.tableTop = tableTop;
    }


    /**
     * Update all slots in the matrix with an heuristic value.
     *
     * @param dest the destination which we are trying to reach
     * @return a multi-scripted array of path units.
     */
    public PathUnit[][] heuristicValue(Coordinate dest) {
        String[][] matrix = tableTop.getMatrix();
        PathUnit[][] matrixPath = new PathUnit[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Coordinate src = new Coordinate(i, j);
                int hVal = PathFindingUtils.manhattanDistance(src, dest);
                PathUnit pathUnit = new PathUnit();
                pathUnit.sethVal(hVal);
                matrixPath[i][j] = pathUnit;
                pathUnit.setCoordinate(src);
            }
        }
        return matrixPath;
    }

    public void print(PathUnit[][] matrixPath) {
        for (int i = 0; i < matrixPath.length; i++) {
            for (int j = 0; j < matrixPath[i].length; j++) {
                System.out.println(matrixPath[i][j].toString());
            }
        }
    }


    boolean notVisited(int[][] visited, int x, int y) {
        return visited[x][y] == 0;
    }


    public void findPath(PathUnit[][] matrixPath, Coordinate src, Coordinate dest, int[][] visited) {
        List<PathUnit> openList = new ArrayList<>();
        int srcPointX = src.xCoordinate;
        int srcPointY = src.yCoordinate;

        if (srcPointX == dest.xCoordinate && srcPointY == dest.yCoordinate) {
            //System.out.println(builder.toString());
            return;
        }

        closedList.add(matrixPath[srcPointX][srcPointY]);
        visited[srcPointX][srcPointY] = 1;

        //go to down
        Coordinate downSlot = new Coordinate(srcPointX + 1, srcPointY);
        boolean downMvt = MovementUtils.coordinatePathIsValid(downSlot);
        if (downMvt && tableTop.slotIsAvailable(downSlot) && notVisited(visited, srcPointX + 1, srcPointY)) {
            int fValDown = matrixPath[srcPointX + 1][srcPointY].gethVal() + MOVE_COST;
            int gValDown = matrixPath[srcPointX + 1][srcPointY].getgVal() + 10;
            matrixPath[srcPointX + 1][srcPointY].setgVal(gValDown);
            matrixPath[srcPointX + 1][srcPointY].setfVal(fValDown);
            openList.add(matrixPath[srcPointX + 1][srcPointY]);
        }

        //go to top
        Coordinate upSlot = new Coordinate(srcPointX - 1, srcPointY);
        boolean upMvt = MovementUtils.coordinatePathIsValid(upSlot);
        if (upMvt && tableTop.slotIsAvailable(upSlot) && notVisited(visited, srcPointX - 1, srcPointY)) {
            int fValTop = matrixPath[srcPointX - 1][srcPointY].gethVal() + MOVE_COST;
            int gValTop = matrixPath[srcPointX - 1][srcPointY].getgVal() + 10;
            matrixPath[srcPointX - 1][srcPointY].setgVal(gValTop);
            matrixPath[srcPointX - 1][srcPointY].setfVal(fValTop);
            openList.add(matrixPath[srcPointX - 1][srcPointY]);
        }

        //go to right
        Coordinate rightSlot = new Coordinate(srcPointX, srcPointY + 1);
        boolean rightMvt = MovementUtils.coordinatePathIsValid(rightSlot);
        if (rightMvt && tableTop.slotIsAvailable(rightSlot) && notVisited(visited, srcPointX, srcPointY + 1)) {
            int fVal = matrixPath[srcPointX][srcPointY + 1].gethVal() + MOVE_COST;
            int gVal = matrixPath[srcPointX][srcPointY + 1].getgVal() + 10;
            matrixPath[srcPointX][srcPointY + 1].setgVal(gVal);
            matrixPath[srcPointX][srcPointY + 1].setfVal(fVal);
            openList.add(matrixPath[srcPointX][srcPointY + 1]);
        }

        //go to left
        Coordinate leftSlot = new Coordinate(srcPointX, srcPointY - 1);
        boolean leftMvt = MovementUtils.coordinatePathIsValid(leftSlot);
        if (leftMvt && tableTop.slotIsAvailable(leftSlot) && notVisited(visited, srcPointX, srcPointY - 1)) {
            int fVal = matrixPath[srcPointX][srcPointY - 1].gethVal() + MOVE_COST;
            int gVal = matrixPath[srcPointX][srcPointY - 1].getgVal() + 10;
            matrixPath[srcPointX][srcPointY - 1].setgVal(gVal);
            matrixPath[srcPointX][srcPointY - 1].setfVal(fVal);
            openList.add(matrixPath[srcPointX][srcPointY - 1]);
        }

        int currentMaxFVal = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < openList.size(); i++) {
            if (openList.get(i).getfVal() < currentMaxFVal) {
                currentMaxFVal = openList.get(i).getfVal();
                index = i;
            }

        }

        builder.append("(" + openList.get(index).getCoordinate().xCoordinate +","+openList.get(index).getCoordinate().yCoordinate +") ");
        findPath(matrixPath, openList.get(index).getCoordinate(), dest, visited);
    }

    public StringBuilder getExplorationResult(){
        return builder;
    }


    public static void main(String[] args) {
        ITableTop tableTop = new TableTop();
        PathScanner pathScanner = new PathScanner(tableTop);
        int[][] visited = new int[tableTop.getMatrix().length][tableTop.getMatrix()[0].length];
        Coordinate src = new Coordinate(0, 0);
        Coordinate dest = new Coordinate(0, 3);
        PathUnit[][] matrixPath = pathScanner.heuristicValue(dest);
        pathScanner.findPath(matrixPath, src, dest, visited);
    }

}
