package com.uss.mars.exploration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An implementation of {@link ITableTop}
 * which is a representation of a multi-scripted array.
 */

public class TableTop implements ITableTop {
    private static final String[][] MATRIX = {
            {"X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X"}
    };

    private static final List<TableTopOccupant> MATRIX_INDEX = new ArrayList<>();

    /**
     * Checks if a particular slot is available or occupied.
     *
     * @param coordinate the X and Y points to be occupied.
     * @return returns true if available and false it is it occupied.
     */
    public boolean slotIsAvailable(final Coordinate coordinate) {
        final String valueText = MATRIX[coordinate.getCoordinateX()][coordinate.getCoordinateY()];
        return valueText.equals("X");
    }

    /**
     * Updates to the matrix will also persist a tiny index which is a reference to the location which was updated.
     * This is done for fast matrix reset rather than having to do a complete grid/array scan.
     *
     * @param occupant the unit occupant one of which must be {@link OccupantType}
     */
    public void update(TableTopOccupant occupant) {
        final Coordinate coordinate = occupant.getCoordinate();
        final OccupantType type = occupant.getOccupantType();
        MATRIX[coordinate.getCoordinateX()][coordinate.getCoordinateY()] = type.getCode();
        updateIndex(occupant);
    }

    /**
     * Will update an index for those units which has been occupied on the matrix for faster referencing.
     *
     * @param occupant the unit occupant one of which must be {@link OccupantType}
     */
    public void updateIndex(TableTopOccupant occupant) {
        MATRIX_INDEX.add(occupant);
    }


    /**
     * Will reset all existing blocks on the table, updating all values to "X"
     */
    public void resetGrid() {
        for (int i = 0; i < MATRIX_INDEX.size(); i++) {
            Coordinate coordinate = MATRIX_INDEX.get(i).getCoordinate();
            MATRIX[coordinate.getCoordinateX()][coordinate.getCoordinateY()] = "X";
        }
    }

    /**
     * Will clear up the index
     */
    public void resetIndex() {
        MATRIX_INDEX.clear();
    }

    /**
     * Will return a reference to the matrix.
     *
     * @return the reference to the matrix.
     */
    public String[][] getMatrix() {
        return MATRIX;
    }

    /**
     * Will return a reference to the matrix index.
     *
     * @return the reference to the matrix index.
     */
    public List<TableTopOccupant> getMatrixIndex() {
        return MATRIX_INDEX;
    }

    public void print() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(TableTop.MATRIX[i]));
        }
    }

}
