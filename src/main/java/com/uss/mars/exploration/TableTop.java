package com.uss.mars.exploration;

import java.util.Arrays;

//Represented as a multi-scripted array
public class TableTop {

public static final String[][] MATRIX_GRID = {
        {"X","X","X","X","X"},
        {"X","X","X","X","X"},
        {"X","X","X","X","X"},
        {"X","X","X","X","X"},
        {"X","X","X","X","X"}
};

    public final int minIndex = 0;
    public final int maxIndex = 4;

    void update(Coordinate coordinate){
        System.out.println(coordinate.toString());

    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(TableTop.MATRIX_GRID[i]));
        }


    }



}
