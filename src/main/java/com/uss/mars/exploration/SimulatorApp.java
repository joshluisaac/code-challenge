package com.uss.mars.exploration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Queue;

public class SimulatorApp {

    public static final Logger LOG = LoggerFactory.getLogger(SimulatorApp.class);

    public static void main(String[] args) {
        Command cmd0 = new Command("PLACE_x",2,2);
        Command cmd1 = new Command("PLACE",0,0);
        Command cmd2 = new Command("PLACE",1,2);
        Command cmd3 = new Command("BLOCK",2,2);
        Command cmd4 = new Command("BLOCK",1,4);


        //in production commands would be passed in from cmdline or external file etc
        CommandQueueService commandQueueService = new CommandQueueService(Arrays.asList(cmd0,cmd1,cmd2,cmd3,cmd4));
        commandQueueService.initialize();

        //retrieve queue size, if size is = 0 then log and throw an exception (runtime)
        Queue<Command> queue = commandQueueService.getQueue();
        int queueSize = queue.size();

        //go through all the commands in the queue and ensure the following
        //1. the coordinates are within the grid to avoid destruction
        //2. the PLACE,BLOCK,EXPLORE and REPORT commands are syntactically valid using regex.
        commandQueueService.validateQueueCommands();

        //ensure the first valid command to the explorer is a PLACE command
        //discard all commands in the queue until that condition is satisfied.
        if(commandQueueService.placeCommandIsHeadEnqueued()){
            for (Command command : queue) {
                LOG.info(command.toString());
            }
        } else {
            LOG.info("Please ensure the first command issued is a PLACE command");
        }




    }

}
