package com.uss.mars.exploration;

public class CommandHandlerFactory {

    public static CommandHandler getHandler(Command command){
        if(command.getName().equals(CommandType.PLACE.toString())) {
            Explorer explorer  = new Explorer(new Coordinate(command.getxAxis(),command.getyAxis()));
            return new PlaceCommandHandler(explorer,null);
        }

        if(command.getName().equals(CommandType.PLACE.toString())) {
            Explorer explorer  = new Explorer(new Coordinate(command.getxAxis(),command.getyAxis()));
            return new PlaceCommandHandler(explorer,null);
        }

        return null; // todo: refactor this
    }

}
