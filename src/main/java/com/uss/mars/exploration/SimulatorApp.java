package com.uss.mars.exploration;

import java.util.Arrays;

public class SimulatorApp {

    public static void main(String[] args) {
        Command cmd1 = new Command("PLACE",0,0);
        Command cmd2 = new Command("PLACE",1,2);
        Command cmd3 = new Command("BLOCK",2,2);
        Command cmd4 = new Command("BLOCK",1,4);


        //in production commands would be passed in from cmdline or external file etc
        CommandQueue commandQueue = new CommandQueue();
        commandQueue.initialize(Arrays.asList(cmd1,cmd2,cmd3,cmd4));
        CommandQueueService commandQueueService = new CommandQueueService(commandQueue);

        //go through all the commands in the queue and ensure the following
        //1. the coordinates are within the grid to avoid destruction
        //2. the PLACE,BLOCK,EXPLORE and REPORT commands are syntactically valid using regex.
        commandQueueService.validateQueueCommands();

    }

}
