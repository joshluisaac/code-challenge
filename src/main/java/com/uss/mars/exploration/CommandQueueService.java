package com.uss.mars.exploration;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommandQueueService {

    private List<Command> commands;
    private Queue<Command> queue = new LinkedList<>();
    public CommandQueueService(List<Command> commands){
        this.commands = commands;
    }


    //Takes a list of commands and then populates a queue
    public void initialize() {
        queue.addAll(commands);
    }

    public Command peek(){
        return queue.peek();
    }


    //checks if the front of the CommandQueue is a PLACE command.
    public boolean placeCommandIsHeadEnqueued(){
        return queue.peek().name.equals("PLACE");
    }

    void validateQueueCommands(){

    }

    public Queue<Command> getQueue(){
        return this.queue;
    }
}