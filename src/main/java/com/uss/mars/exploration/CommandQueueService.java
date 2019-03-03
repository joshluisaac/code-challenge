package com.uss.mars.exploration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommandQueueService {

    private static final Logger LOG = LoggerFactory.getLogger(CommandQueueService.class);

    private List<Command> commands;
    private Queue<Command> queue = new LinkedList<>();
    public CommandQueueService(List<Command> commands){
        this.commands = commands;
    }


    //Takes a list of commands and then populates a queue
    public void initialize() {
        queue.addAll(commands);
    }


    //checks if the front of the CommandQueue is a PLACE command.
    public boolean placeCommandIsHeadEnqueued(){
        return queue.peek().name.equals("PLACE");
    }

    public boolean validateCoordinates(){
        for (Command command : queue) {
            final String placeCmd = CommandType.PLACE.toString();
            final String blockCmd = CommandType.BLOCK.toString();
            final String exploreCmd = CommandType.EXPLORE.toString();
            if (command.getName().equals(placeCmd) || command.getName().equals(blockCmd) || command.getName().equals(exploreCmd) ){
                if (!MovementUtils.coordinatePathIsValid(new Coordinate(command.getxAxis(),command.getyAxis()))){
                    LOG.error("The specified X and Y coordinates are out of grid coverage.");
                    throw new CoordinateOutsideBoundsException("The specified X and Y coordinates are out of grid coverage. Explorer would be destroyed.");
                }
            }
        }
        return true;
    }

    void validateQueueCommands(){
        for (Command command : queue) {
            LOG.info(command.getName());
        }
    }

    public Queue<Command> getQueue(){
        return this.queue;
    }
}