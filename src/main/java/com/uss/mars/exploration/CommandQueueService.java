package com.uss.mars.exploration;

public class CommandQueueService {

    private CommandQueue commandQueue;
    public CommandQueueService(CommandQueue commandQueue){
        this.commandQueue = commandQueue;
    }

    //checks if the front of the CommandQueue is a PLACE command.
    boolean placeCommandIsHeadEnqued(){
        return commandQueue.peek().name.equals("PLACE");
    }

}
