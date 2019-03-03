package com.uss.mars.exploration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of {@link CommandHandler} that handles all <tt>PLACE<tt/>
 * command requests in {@link CommandQueueService#queue}
 */
public class PlaceCommandHandler implements CommandHandler {


    private static final Logger LOG = LoggerFactory.getLogger(PlaceCommandHandler.class);
    private MarsExplorer exp;
    private TableTop tableTop;

    public PlaceCommandHandler(MarsExplorer exp, TableTop tableTop) {
        this.exp = exp;
        this.tableTop = tableTop;
    }

    /**
     * Executes a bunch of methods and checks.
     * Checks if a slot location is available and
     * upon availability will reset grid and place explorer at that location.
     * Otherwise a message is logged to console indicating location is blocked and update will not proceed.
     */
    @Override
    public void execute() {
        Coordinate coordinate = exp.getCoordinate();
        if (slotIsAvailable()) {
            tableTop.resetGrid();
            tableTop.update(coordinate, exp.getOccupantType());
        } else {
            LOG.info("Slot position X:{} and Y:{} is blocked and occupied", coordinate.getCoordinateX(), coordinate.getCoordinateY());
        }
    }

    /**
     * A forwarding method which is a wrapper around {@link TableTop#slotIsAvailable(Coordinate)}  }
     * Checks if a slot location is available.
     * <p>
     * A slot is defined available if it is not currently being occupied by any blocks.
     * Please note, i used the word unit in the requirements to mean slots.
     */
    private boolean slotIsAvailable() {
        return tableTop.slotIsAvailable(exp.getCoordinate());
    }

    private SlotStatus slotAvailabilityStatus() {
        return SlotStatus.BLOCKED;
    }

}
