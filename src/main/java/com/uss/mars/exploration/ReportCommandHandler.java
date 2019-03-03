package com.uss.mars.exploration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

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
        StringBuilder buff = new StringBuilder();
        for (TableTopOccupant occupant : tableTop.getMatrixIndex()) {
            String code = occupant.getOccupantType().getCode();
            int xAxis = occupant.getCoordinate().getCoordinateX();
            int yAxis = occupant.getCoordinate().getCoordinateY();
            String formattedText = MessageFormat.format("{0}:({1},{2})",code,xAxis,yAxis);
            buff.append(formattedText + " ");
        }
        System.out.println(buff.toString());

    }

}
