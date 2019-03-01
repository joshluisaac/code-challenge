package com.uss.mars.exploration;

import java.util.Arrays;

//Represented as a multi-scripted array
public class TableTop {

private static final String[][] MATRIX_GRID = {
        {"X","X","X","X","X"},
        {"X","X","X","X","X"},
        {"X","X","X","X","X"},
        {"X","X","X","X","X"},
        {"X","X","X","X","X"}
};

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(TableTop.MATRIX_GRID[i]));
        }


    }



}
