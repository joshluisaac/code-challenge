package com.uss.mars.exploration.commandhandlers;

import com.uss.mars.exploration.Blocker;
import com.uss.mars.exploration.services.CommandQueueService;
import com.uss.mars.exploration.Coordinate;
import com.uss.mars.exploration.TableTop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of {@link CommandHandler} that handles all <tt>BLOCK<tt/>
 * command requests in {@link CommandQueueService#queue}
 */
public class BlockCommandHandler  implements CommandHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BlockCommandHandler.class);
    private final Blocker blocker;
    private final TableTop tableTop;

    public BlockCommandHandler(Blocker blocker, TableTop tableTop){
        this.blocker = blocker;
        this.tableTop = tableTop;
    }

    /**
     * Will position an obstruction to the part of an explorer if that position has not been occupied.
     * It first checks if that coordinate is available.If true it proceeds with placing the obstruction otherwise
     * it will be skipped and ignored while message is logged to console.
     */
    @Override
    public void execute() {
        final Coordinate coordinate = blocker.getCoordinate();
        if(tableTop.slotIsAvailable(coordinate)) {
            tableTop.update(blocker);
        } else {
            LOG.info("SKIPPED {}: Slot position X:{} and Y:{} is occupied.", blocker.getOccupantType(), coordinate.getCoordinateX(), coordinate.getCoordinateY());
        }
    }
}
