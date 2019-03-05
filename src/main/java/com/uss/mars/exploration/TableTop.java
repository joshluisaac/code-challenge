package com.uss.mars.exploration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Represented as a multi-scripted array
public class TableTop {
    private static final String[][] MATRIX = {
            {"X","X","X","X","X"},
            {"X","X","X","X","X"},
            {"X","X","X","X","X"},
            {"X","X","X","X","X"},
            {"X","X","X","X","X"}
    };

private static final List<TableTopOccupant> MATRIX_INDEX = new ArrayList<>();


    public boolean slotIsAvailable(final Coordinate coordinate) {
        final String valueText = MATRIX[coordinate.getCoordinateX()][coordinate.getCoordinateY()];
        return valueText.equals("X");
    }

    //Updates to the matrix will also persist a tiny index which is a reference to the location which was updated.
    //This is done for fast matrix reset rather than having to do a complete grid/array scan.
    public void update(TableTopOccupant occupant){
        final Coordinate coordinate = occupant.getCoordinate();
        final OccupantType type = occupant.getOccupantType();
        MATRIX[coordinate.getCoordinateX()][coordinate.getCoordinateY()] = type.getCode();
        updateIndex(occupant);
    }

    public void updateIndex(TableTopOccupant occupant){
        MATRIX_INDEX.add(occupant);
    }

    //will reset all exiting blocks on the table.
    public void resetGrid() {
        for (int i = 0; i < MATRIX_INDEX.size(); i++) {
            Coordinate coordinate = MATRIX_INDEX.get(i).getCoordinate();
            MATRIX[coordinate.getCoordinateX()][coordinate.getCoordinateY()] = "X";
        }
    }

    public void resetIndex(){
        MATRIX_INDEX.clear();
    }

    public String[][] getMatrix(){
        return MATRIX;
    }

    public List<TableTopOccupant> getMatrixIndex(){
        return MATRIX_INDEX;
    }

    public void print(){
        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(TableTop.MATRIX[i]));
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(TableTop.MATRIX[i]));
        }


    }



}
