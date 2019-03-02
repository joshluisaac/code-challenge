package com.uss.mars.exploration;

public class CommandHandlerFactory {

    public static CommandHandler getHandler(Command command) {
        if (command.getName().equals(CommandType.PLACE.toString())) {
            return new PlaceCommandHandler(new MarsExplorer(new Coordinate(command.getxAxis(), command.getyAxis())),null);
        }

        if (command.getName().equals(CommandType.BLOCK.toString())) {
            return new BlockCommandHandler(new Blocker(new Coordinate(command.getxAxis(), command.getyAxis())));
        }

        if (command.getName().equals(CommandType.EXPLORE.toString())) {
            return new ExploreCommandHandler();
        }

        if (command.getName().equals(CommandType.REPORT.toString())) {
            return new ReportCommandHandler();
        }
        return null; // todo: refactor this
    }

}
