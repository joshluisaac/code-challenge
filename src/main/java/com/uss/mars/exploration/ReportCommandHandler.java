package com.uss.mars.exploration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of {@link CommandHandler} that handles all <tt>REPORT<tt/>
 * command requests in {@link CommandQueueService#queue}
 */

public class ReportCommandHandler implements CommandHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ReportCommandHandler.class);
    private final TableTop tableTop;

    public ReportCommandHandler(TableTop tableTop) {
        this.tableTop = tableTop;
    }

    /**
     * Report the state of the tabletop/grid and format output.
     * It does this by reading the index and formatting the result rather than
     * traversing the entire matrix structure.
     */
    @Override
    public void execute() {
        System.out.println(tableTop.getMatrixIndex().toString());

    }

}
