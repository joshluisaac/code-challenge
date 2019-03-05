package com.uss.mars.exploration.services;

import com.uss.mars.exploration.*;
import com.uss.mars.exploration.exceptions.CoordinateOutsideBoundsException;
import com.uss.mars.exploration.exceptions.InvalidSyntaxException;
import com.uss.mars.exploration.utils.MovementUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * An implementation of {@link ICommandQueueService} that
 * handles and validates all commands staged in the queue.
 */
public class CommandQueueService implements ICommandQueueService {

    private static final Logger LOG = LoggerFactory.getLogger(CommandQueueService.class);
    private List<Command> commands;
    private Queue<Command> queue = new LinkedList<>();

    public CommandQueueService(List<Command> commands) {
        this.commands = commands;
    }


    /**
     * Takes a of {@link List} commands which
     * was passed into the simulator to populate the queue.
     *
     */
    @Override
    public void initialize() {
        queue.addAll(commands);
    }


    /**
     * Checks if the front of the CommandQueue is a PLACE command.
     *
     * @return true or false.
     */
    @Override
    public boolean placeCommandIsHeadEnqueued() {
        return queue.peek().name.equals("PLACE");
    }

    /**
     * Checks if the command at the peek of the queue is a at origin.
     * A {@link CommandType#PLACE} is said to be at origin if it's coordinates are <tt>(0,0)</tt>
     *
     * @return the evaluation value as either true or false.
     * @throws IllegalArgumentException if the specified command is not at origin.
     */
    @Override
    public boolean isPlaceCommandAtOrigin(){
        Command command = queue.peek();
        boolean result = command.getxAxis() == 0 && command.getyAxis() == 0;
        if(!result) {
            LOG.error("The specified X:{} and Y:{} coordinates for {} is not at origin.Origin is defined as (0,0)", command.getxAxis(), command.getyAxis(), command.name);
            String errorMsg = MessageFormat.format("The specified X:{0} and Y:{1} coordinates for {2} is not at origin.Origin is defined as (0,0)",command.getxAxis(), command.getyAxis(), command.name);
            throw new IllegalArgumentException(errorMsg);
        }
        return result;
    }

    /**
     * Checks the coordinates of the PLACE, BLOCK and EXPLORE commands passed into the system.
     * This check ensures the coordinates are valid and not out of grid bounds.
     *
     * @return the evaluation value as either true or false.
     * @throws CoordinateOutsideBoundsException if the specified command is out of bounds.
     */
    @Override
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

    /**
     * Checks all the commands which has been passed to the simulator for execution.
     * This check uses regular expression and certain rules depending on the {@link CommandType}
     *
     * @return the evaluation value as either true or false.
     * @throws InvalidSyntaxException which extends {@link RuntimeException} if the specified command is invalid.
     */
    @Override
    public boolean validateQueueCommandSyntax() {

        return true;
    }

    void getValidPlaceCommand(){

    }

    /**
     * A query method which returns a reference to the command queue.
     *
     * @return reference to the queue
     */
    @Override
    public Queue<Command> getQueue() {
        return this.queue;
    }
}