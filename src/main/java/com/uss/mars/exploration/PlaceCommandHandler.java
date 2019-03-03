package com.uss.mars.exploration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Explorer movement class
//This class is dependent on the Explorer object and CommandQueueService
public class PlaceCommandHandler implements CommandHandler {


    private static final Logger LOG = LoggerFactory.getLogger(PlaceCommandHandler.class);
    private MarsExplorer exp;
    private TableTop tableTop;

    public PlaceCommandHandler(MarsExplorer exp, TableTop tableTop) {
        this.exp = exp;
        this.tableTop = tableTop;
    }


    // check coordinate validity
    // check if coordinate isn't already occupied. slotIsAvailable
    // if true check if the head of the CommandQueue is PLACE
    // reset blocks. That is, reinitialize the grid/TableTop.
    // move to coordinate position

    @Override
    public void execute() {
        Coordinate coordinate = exp.getCoordinate();
        if (slotIsAvailable()) {
            resetGrid();
            moveToPosition();
            tableTop.update(coordinate);
        } else {
            LOG.info("Slot position X:{} and Y:{} is blocked and occupied", coordinate.getCoordinateX(),coordinate.getCoordinateY());
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

    //will reset all exiting blocks on the table.
    void resetGrid() {

    }

    void moveToPosition() {

    }

}
