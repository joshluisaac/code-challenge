package com.uss.mars.exploration;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommandQueue {

    private Queue<Command> queue = new LinkedList<>();

    //Takes a list of commands and then populates a queue
    public void initialize(List<Command> commands) {
        queue.addAll(commands);
        //for (Command command : commands) {
          //  commandQueue.add(command);
        //}

    }


    public Command peek(){
    return queue.peek();
    }

}
