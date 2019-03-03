package com.uss.mars.exploration;

//Explorer movement class
//This class is dependent on the Explorer object and CommandQueueService
public class PlaceCommandHandler implements CommandHandler {

    private MarsExplorer exp;
    private String[][] grid;

    public PlaceCommandHandler(MarsExplorer exp, String[][] grid) {
        this.exp = exp;
        this.grid = grid;
    }

    @Override
    public void execute() {

    }

    // check coordinate validity
    // check if coordinate isn't already occupied. slotIsAvailable
    // if true check if the head of the CommandQueue is PLACE
    // reset blocks. That is, reinitialize the grid/TableTop.
    // move to coordinate position
    void occupySlot() {
        //exp.coordinateIsValid();
        // if coordinate is valid and commandQueue is empty
        if (slotIsAvailable()) {
            resetGrid();
            moveToCoordinatePosition();
        }
    }

    // Checks for slot availability
    // Takes an explorer and then checks if the slot it is asking for is available.
    // A slot is defined available if it is not currently being occupied by any
    // blocks.
    // Please note, i used the word unit in the requirements to mean slots
    boolean slotIsAvailable() {
        return false;
    }

    SlotStatus slotAvailabilityStatus() {

        return SlotStatus.BLOCKED;
    }

    void resetGrid() {

    }

    void moveToCoordinatePosition() {

    }

}
