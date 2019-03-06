package com.uss.mars.exploration.commandhandlers;

import com.uss.mars.exploration.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExploreCommandHandler implements CommandHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExploreCommandHandler.class);

    private final Explorer explorer;
    private final ITableTop tableTop;

    public ExploreCommandHandler(Explorer explorer, ITableTop tableTop) {
        this.explorer = explorer;
        this.tableTop = tableTop;

    }

    @Override
    public void execute() {
        Coordinate dest = explorer.getCoordinate();

        //skip exploration if slot is blocked.
        if (!tableTop.slotIsAvailable(dest)){
            LOG.info("Exploration is skipped because the requested position is blocked.");
           return;
        }
        PathScanner pathScanner = new PathScanner(tableTop);
        int[][] visited = new int[tableTop.getMatrix().length][tableTop.getMatrix()[0].length];

        Coordinate src = getExplorerLastPosition();
        PathUnit[][] matrixPath = pathScanner.heuristicValue(dest);
        pathScanner.findPath(matrixPath, src, dest,visited);

        StringBuilder builder = new StringBuilder();
        builder.append("PATH: ");
        builder.append("(" + src.xCoordinate +","+src.yCoordinate +") ");
        builder.append(pathScanner.getExplorationResult());
        LOG.info(builder.toString());
        System.out.println(builder.toString());

    }

    // will retrieve the last valid position of the explorer
    public Coordinate getExplorerLastPosition(){
        List<TableTopOccupant> indexList = tableTop.getMatrixIndex();
        int size = indexList.size();
        for (int i = 0; i < size; i++) {
            if(indexList.get(i).getOccupantType().getCode().equals("E")) {
                return indexList.get(i).getCoordinate();
            }
        }
        return null;
    }


}
