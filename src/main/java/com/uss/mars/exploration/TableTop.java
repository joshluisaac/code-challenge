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

private static final List<Coordinate> MATRIX_INDEX = new ArrayList<>();

    public final int minIndex = 0;
    public final int maxIndex = 4;


    public boolean slotIsAvailable(final Coordinate coordinate) {
        final String valueText = MATRIX[coordinate.getCoordinateX()][coordinate.getCoordinateY()];
        return valueText.equals("X");
    }

    //Updates to the matrix will also persist a tiny index which is a reference to the location which was updated.
    //This is done for fast matrix reset rather than having to do a complete grid/array scan.
    public void update(Coordinate coordinate,OccupantType type){
        MATRIX[coordinate.getCoordinateX()][coordinate.getCoordinateY()] = type.getCode();
        MATRIX_INDEX.add(coordinate);
    }

    //will reset all exiting blocks on the table.
    public void resetGrid() {
        for (int i = 0; i < MATRIX_INDEX.size(); i++) {
            Coordinate coordinate = MATRIX_INDEX.get(i);
            MATRIX[coordinate.getCoordinateX()][coordinate.getCoordinateY()] = "X";
        }
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
