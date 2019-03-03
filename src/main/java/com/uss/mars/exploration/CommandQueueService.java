package com.uss.mars.exploration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommandQueueService {

    private static final Logger LOG = LoggerFactory.getLogger(CommandQueueService.class);

    private List<Command> commands;
    private Queue<Command> queue = new LinkedList<>();

    public CommandQueueService(List<Command> commands) {
        this.commands = commands;
    }


    //Takes a list of commands and then populates a queue
    public void initialize() {
        queue.addAll(commands);
    }


    //checks if the front of the CommandQueue is a PLACE command.
    public boolean placeCommandIsHeadEnqueued() {
        return queue.peek().name.equals("PLACE");
    }

    public boolean isPlaceCommandAtOrigin(){
        Command command = queue.peek();
        boolean result = command.getxAxis() == 0 && command.getyAxis() == 0;
        if(!result) {
            LOG.error("The specified X:{} and Y:{} coordinates for {} is not at origin.", command.getxAxis(), command.getyAxis(), command.name);
            String errorMsg = MessageFormat.format("The specified X:{0} and Y:{1} coordinates for {2} is not at origin.",command.getxAxis(), command.getyAxis(), command.name);
            throw new IllegalArgumentException(errorMsg);
        }
        return result;
    }

    //skipAllCommandsInQueueUntilValidNonOriginPlaceIsIssued. TODO

    public boolean validateCoordinates() {
        for (Command command : queue) {
            final String placeCmd = CommandType.PLACE.toString();
            final String blockCmd = CommandType.BLOCK.toString();
            final String exploreCmd = CommandType.EXPLORE.toString();
            if (command.getName().equals(placeCmd) || command.getName().equals(blockCmd) || command.getName().equals(exploreCmd)) {
                if (!MovementUtils.coordinatePathIsValid(new Coordinate(command.getxAxis(), command.getyAxis()))) {
                    LOG.error("The specified X:{} and Y:{} coordinates for {} is out of grid coverage.", command.getxAxis(), command.getyAxis(), command.name);
                    String errorMsg = MessageFormat.format("The specified X:{0} and Y:{1} coordinates for {2} is out of grid coverage. Explorer would be destroyed.",command.getxAxis(), command.getyAxis(), command.name);
                    throw new CoordinateOutsideBoundsException(errorMsg);
                }
            }
        }
        LOG.info("Coordinates validated successfully.");
        return true;
    }

    void validateQueueCommandSyntax() {
    }

    public Queue<Command> getQueue() {
        return this.queue;
    }
}