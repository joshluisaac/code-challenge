package com.uss.mars.exploration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockCommandHandler  implements CommandHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BlockCommandHandler.class);
    private final Blocker blocker;
    private final TableTop tableTop;

    public BlockCommandHandler(Blocker blocker, TableTop tableTop){
        this.blocker = blocker;
        this.tableTop = tableTop;
    }

    @Override
    public void execute() {
        Coordinate coordinate = blocker.getCoordinate();
        if(tableTop.slotIsAvailable(coordinate)) {
            tableTop.update(blocker);
        } else {
            LOG.info("SKIPPED {}: Slot position X:{} and Y:{} is occupied.", blocker.getOccupantType(), coordinate.getCoordinateX(), coordinate.getCoordinateY());
        }



    }
}
