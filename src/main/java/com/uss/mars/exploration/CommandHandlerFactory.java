package com.uss.mars.exploration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandHandlerFactory {

    public static final Logger LOG = LoggerFactory.getLogger(CommandHandlerFactory.class);

    private CommandHandlerFactory(){}

    public static CommandHandler getHandler(Command command, TableTop tableTop) {
        if (command.getName().equals(CommandType.PLACE.toString())) {
            LOG.info("{} Command issued: processing using PlaceCommandHandler",CommandType.PLACE);
            return new PlaceCommandHandler(new MarsExplorer(new Coordinate(command.getxAxis(), command.getyAxis())),tableTop);
        }

        if (command.getName().equals(CommandType.BLOCK.toString())) {
            LOG.info("{} Command issued: processing using BlockCommandHandler",CommandType.BLOCK);
            return new BlockCommandHandler(new Blocker(new Coordinate(command.getxAxis(), command.getyAxis())));
        }

        if (command.getName().equals(CommandType.EXPLORE.toString())) {
            LOG.info("{} Command issued: processing using ExploreCommandHandler",CommandType.EXPLORE);
            return new ExploreCommandHandler();
        }

        if (command.getName().equals(CommandType.REPORT.toString())) {
            LOG.info("{} Command issued: processing using ReportCommandHandler",CommandType.REPORT);
            return new ReportCommandHandler();
        }
        return null; // todo: refactor this
    }

}
