package com.uss.mars.exploration;

import com.uss.mars.exploration.commandhandlers.CommandHandlerFactory;
import com.uss.mars.exploration.services.CommandQueueService;
import com.uss.mars.exploration.services.ICommandQueueService;
import com.uss.mars.exploration.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Queue;

public class SimulatorApp {

    private static final Logger LOG = LoggerFactory.getLogger(SimulatorApp.class);


    private static void displayUsage() {
        LOG.info("Usage: java -cp %classpath% com.uss.mars.exploration.SimulatorApp sampledata.txt");
    }

    /**
     * Retrieves the size of the queue and checks if it is greater than 0.
     * If it is 0 or less then an {@link IllegalArgumentException} exception is thrown.
     *
     * @param commandQueueService command queue service.
     *
     * @return true or throws an exception.
     */
    public boolean validateCommandQueueSize(ICommandQueueService commandQueueService){
        final int queueSize = getQueue(commandQueueService).size();
        if (queueSize == 0) {
            LOG.error("Command queue is empty, size {}. Please initialize with some valid commands.", queueSize);
            throw new IllegalArgumentException("Command queue is empty. Please initialize with some valid commands.");
        }
        return true;
    }

    /**
     * Retrieves the {@link Queue} of {@link Command}
     *
     * @param commandQueueService command queue service.
     *
     * @return command queue
     */
    public Queue<Command> getQueue(ICommandQueueService commandQueueService){
         return commandQueueService.getQueue();
    }

    /**
     * Checks if the head of the queue is of type {@link CommandType#PLACE}
     *
     * @param commandQueueService command queue service.
     */
    public void checkHeadOfQueue(ICommandQueueService commandQueueService){
        if (!commandQueueService.placeCommandIsHeadEnqueued())
            LOG.error("Operation discarded. Please ensure the first command issued is a PLACE command.");
        throw new IllegalArgumentException("Operation discarded. Please ensure the first command issued is a PLACE command.");
    }

    /**
     * Processes the content of the queue
     *
     */
    public void process(ICommandQueueService commandQueueService,ITableTop grid){
        LOG.info("Command at the head of the queue is 'PLACE'");
        for (Command command : getQueue(commandQueueService)) {
            CommandHandlerFactory.getHandler(command,grid).execute();
        }
    }


    public static void main(String[] args) throws Exception {
        String fileName = null;
        for (int param = 0; param < args.length; param++) {
            if (fileName == null) fileName = args[param];
        }
        if (fileName == null) {
            LOG.error("please provide input file name.");
            displayUsage();
            return;
        }

        final ITableTop grid = new TableTop();
        final SimulatorApp simulatorApp = new SimulatorApp();
        final List<String> contents = FileUtils.readFile(new File(fileName));
        final List<Command> commands = FileUtils.commandMapper(contents);
        final ICommandQueueService commandQueueService = new CommandQueueService(commands);
        commandQueueService.initialize();
        simulatorApp.validateCommandQueueSize(commandQueueService);


        //go through all the commands in the queue and ensure the following
        //1. the PLACE,BLOCK,EXPLORE and REPORT commands are syntactically valid using regex.
        commandQueueService.validateQueueCommandSyntax();

        //2. place command must start at origin.
        commandQueueService.isPlaceCommandAtOrigin();

        //3. the coordinates are within the grid to avoid destruction
        commandQueueService.validateCoordinates();

        //ensure the first valid command to the explorer is a PLACE command
        //discard all commands in the queue until that condition is satisfied.
        simulatorApp.checkHeadOfQueue(commandQueueService);

        simulatorApp.process(commandQueueService,grid);

    }

}
