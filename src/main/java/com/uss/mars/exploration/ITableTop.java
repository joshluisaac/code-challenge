package com.uss.mars.exploration;

import java.util.List;

public interface ITableTop {

    public boolean slotIsAvailable(final Coordinate coordinate);
    public void update(TableTopOccupant occupant);
    public void updateIndex(TableTopOccupant occupant);
    public void resetGrid();
    public void resetIndex();
    public String[][] getMatrix();
    public List<TableTopOccupant> getMatrixIndex();
    public void print();

}
