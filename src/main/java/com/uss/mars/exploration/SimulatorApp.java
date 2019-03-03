package com.uss.mars.exploration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Queue;

public class SimulatorApp {

    private static final Logger LOG = LoggerFactory.getLogger(SimulatorApp.class);

    public static void main(String[] args) {
        final TableTop grid = new TableTop();
        Command cmd0 = new Command("PLACE_",2,4);
        Command cmd1 = new Command("PLACE",0,0);
        Command cmd2 = new Command("PLACE",1,2);
        Command cmd3 = new Command("BLOCK",2,2);
        Command cmd4 = new Command("BLOCK",1,4);
        Command cmd5 = new Command("EXPLORE");
        Command cmd6 = new Command("REPORT");


        //in production commands would be passed in from cmdline or external file etc
        CommandQueueService commandQueueService = new CommandQueueService(Arrays.asList(cmd0,cmd1,cmd2,cmd3,cmd4,cmd5,cmd6));
        commandQueueService.initialize();

        //retrieve queue size, if size is = 0 then log and throw an exception (runtime)
        Queue<Command> queue = commandQueueService.getQueue();
        final int queueSize = queue.size();

        if (queueSize == 0) {
            LOG.error("Command queue is empty, size {}. Please initialize with some valid commands.", queueSize);
            throw new IllegalArgumentException("Command queue is empty. Please initialize with some valid commands.");
        }

        //2. the PLACE,BLOCK,EXPLORE and REPORT commands are syntactically valid using regex.
        commandQueueService.validateQueueCommandSyntax();

        //go through all the commands in the queue and ensure the following
        //1. the coordinates are within the grid to avoid destruction
        commandQueueService.validateCoordinates();


        //ensure the first valid command to the explorer is a PLACE command
        //discard all commands in the queue until that condition is satisfied.
        if(commandQueueService.placeCommandIsHeadEnqueued()){
            for (Command command : queue) {
                CommandHandlerFactory.getHandler(command,grid).execute();
            }
        } else {
            LOG.error("Operation discarded. Please ensure the first command issued is a PLACE command.");
        }




    }

}
